package dungeonmania.entities.logical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeonmania.entities.Entity;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Switch door that opens/closes based on logical conditions.
 * Cannot be opened with keys - only through logical activation.
 */
public class SwitchDoor extends Entity implements LogicalEntity {
    private LogicRule logicRule;
    private boolean isOpen = false;
    private Map<Position, Integer> activationTicks = new HashMap<>();

    public SwitchDoor(Position position, LogicRule logicRule) {
        super(position.asLayer(Entity.DOOR_LAYER));
        this.logicRule = logicRule;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        // Spiders can always move through
        if (entity instanceof Spider) {
            return true;
        }
        // Players can only move through if door is open
        return isOpen;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        // No special interaction needed
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        // No action needed
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        // No cleanup needed
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public LogicRule getLogicRule() {
        return logicRule;
    }

    @Override
    public boolean isLogicallyActivated() {
        return isOpen;
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

        if (shouldActivate && !isOpen) {
            activateLogical(map);
        } else if (!shouldActivate && isOpen) {
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
        isOpen = true;
    }

    @Override
    public void deactivateLogical(GameMap map) {
        isOpen = false;
    }
}
