package dungeonmania.entities.logical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Light bulb entity that can be lit up by satisfying logical conditions.
 * Always created as a logical entity with a specified logic rule.
 */
public class LightBulb extends Entity implements LogicalEntity {
    private LogicRule logicRule;
    private boolean isActivated = false;
    private Map<Position, Integer> activationTicks = new HashMap<>();

    public LightBulb(Position position, LogicRule logicRule) {
        super(position.asLayer(Entity.ITEM_LAYER));
        this.logicRule = logicRule;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        // Light bulbs don't interact with overlapping entities
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        // No action needed
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        // No cleanup needed
    }

    @Override
    public LogicRule getLogicRule() {
        return logicRule;
    }

    @Override
    public boolean isLogicallyActivated() {
        return isActivated;
    }

    @Override
    public void updateLogicalState(GameMap map, int tick) {
        List<Conductor> adjacentEntities = getPosition().getCardinallyAdjacentPositions().stream()
                .flatMap(pos -> map.getEntities(pos).stream()).filter(e -> e instanceof Conductor)
                .map(Conductor.class::cast).toList();

        List<Conductor> activatedConductors = adjacentEntities.stream().filter(Conductor::isActivated).toList();

        boolean shouldActivate = switch (logicRule) {
        case OR -> activatedConductors.size() >= 1;
        case AND -> activatedConductors.size() >= 2 && activatedConductors.size() == adjacentEntities.size();
        case XOR -> activatedConductors.size() == 1;
        case CO_AND -> checkCoAndCondition(activatedConductors, tick);
        };

        if (shouldActivate && !isActivated) {
            activateLogical(map);
        } else if (!shouldActivate && isActivated) {
            deactivateLogical(map);
        }

        // Update activation ticks for CO_AND logic
        updateActivationTicks(activatedConductors, tick);
    }

    private boolean checkCoAndCondition(List<Conductor> activatedConductors, int tick) {
        if (activatedConductors.size() < 2) {
            return false;
        }

        // Check if at least 2 conductors were activated on the same tick
        Map<Integer, Long> tickCounts = new HashMap<>();
        for (Conductor conductor : activatedConductors) {
            Position pos = ((Entity) conductor).getPosition();
            Integer activationTick = activationTicks.get(pos);
            if (activationTick != null) {
                tickCounts.put(activationTick, tickCounts.getOrDefault(activationTick, 0L) + 1);
            }
        }

        return tickCounts.values().stream().anyMatch(count -> count >= 2);
    }

    private void updateActivationTicks(List<Conductor> activatedConductors, int tick) {
        for (Conductor conductor : activatedConductors) {
            Position pos = ((Entity) conductor).getPosition();
            if (!activationTicks.containsKey(pos)) {
                activationTicks.put(pos, tick);
            }
        }

        // Remove deactivated conductors from tracking
        activationTicks.keySet()
                .removeIf(pos -> activatedConductors.stream().noneMatch(c -> ((Entity) c).getPosition().equals(pos)));
    }

    @Override
    public void activateLogical(GameMap map) {
        isActivated = true;
    }

    @Override
    public void deactivateLogical(GameMap map) {
        isActivated = false;
    }
}
