package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.Switch;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.entities.logical.Conductor;
import dungeonmania.entities.logical.LogicRule;
import dungeonmania.entities.logical.LogicalEntity;
import dungeonmania.map.GameMap;

public class Bomb extends InventoryItem implements LogicalEntity {
    public enum State {
        SPAWNED, PLACED
    }

    public static final int DEFAULT_RADIUS = 1;
    private State state;
    private int radius;
    private LogicRule logicRule;
    private boolean isLogicallyActivated = false;
    private Map<Position, Integer> activationTicks = new HashMap<>();

    /**
     * Constructor for regular bombs (non-logical)
     */
    public Bomb(Position position, int radius) {
        this(position, radius, null);
    }

    /**
     * Constructor for logical bombs
     */
    public Bomb(Position position, int radius, LogicRule logicRule) {
        super(position);
        state = State.SPAWNED;
        this.radius = radius;
        this.logicRule = logicRule;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (state != State.SPAWNED)
            return;
        if (entity instanceof Player player) {
            if (!player.pickUp(this))
                return;
            map.destroyEntity(this);
        }
    }

    public int getRadius() {
        return radius;
    }

    public boolean isLogicalBomb() {
        return logicRule != null;
    }

    public void onPutDown(GameMap map, Position p) {
        setPosition(p);
        map.addEntity(this);
        this.state = State.PLACED;

        if (isLogicalBomb()) {
            // For logical bombs, just update state - explosion happens through logical activation
            updateLogicalState(map, map.getGame().getTick());
        } else {
            // For regular bombs, subscribe to adjacent switches
            List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
            adjPosList.stream().forEach(node -> {
                List<Entity> entities = map.getEntities(node).stream().filter(Switch.class::isInstance).toList();
                entities.stream().map(Switch.class::cast).forEach(s -> s.subscribe(this, map));
            });
        }
    }

    public State getState() {
        return state;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1, false, false));
    }

    @Override
    public int getDurability() {
        return Integer.MAX_VALUE;
    }

    // LogicalEntity implementation

    @Override
    public LogicRule getLogicRule() {
        return logicRule;
    }

    @Override
    public boolean isLogicallyActivated() {
        return isLogicallyActivated;
    }

    @Override
    public void updateLogicalState(GameMap map, int tick) {
        if (!isLogicalBomb() || state != State.PLACED) {
            return;
        }

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

        if (shouldActivate && !isLogicallyActivated) {
            activateLogical(map);
        } else if (!shouldActivate && isLogicallyActivated) {
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
        isLogicallyActivated = true;
        // Explode the bomb
        explode(map);
    }

    @Override
    public void deactivateLogical(GameMap map) {
        isLogicallyActivated = false;
    }

    /**
     * Cause the bomb to explode, destroying entities in radius
     */
    private void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                map.destroyEntitiesOnPosition(i, j);
            }
        }
    }
}
