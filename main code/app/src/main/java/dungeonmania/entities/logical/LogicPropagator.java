package dungeonmania.entities.logical;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Utility class for propagating electrical current through wires and logical entities.
 */
public class LogicPropagator {
    /**
     * Propagate activation from a conductor to adjacent entities
     * @param map The game map
     * @param source The source conductor
     * @param tick The tick when activation occurred
     */
    public static void propagateActivation(GameMap map, Conductor source, int tick) {
        Set<Position> visited = new HashSet<>();
        propagateActivationRecursive(map, ((Entity) source).getPosition(), visited, tick);
    }

    private static void propagateActivationRecursive(GameMap map, Position position, Set<Position> visited, int tick) {
        if (visited.contains(position)) {
            return;
        }
        visited.add(position);

        // Get cardinally adjacent positions
        List<Position> adjacentPositions = position.getCardinallyAdjacentPositions();

        for (Position adjPos : adjacentPositions) {
            List<Entity> entities = map.getEntities(adjPos);

            for (Entity entity : entities) {
                // Activate adjacent wires
                if (entity instanceof Wire wire && !wire.isActivated()) {
                    wire.activate(map, tick);
                    propagateActivationRecursive(map, adjPos, visited, tick);
                }

                // Activate adjacent logical entities
                if (entity instanceof LogicalEntity logicalEntity) {
                    logicalEntity.updateLogicalState(map, tick);
                }
            }
        }
    }

    /**
     * Propagate deactivation from a conductor to adjacent entities
     * @param map The game map
     * @param source The source conductor
     */
    public static void propagateDeactivation(GameMap map, Conductor source) {
        Set<Position> visited = new HashSet<>();
        propagateDeactivationRecursive(map, ((Entity) source).getPosition(), visited);
        // After deactivating wires, update all logical entities
        updateAllLogicalStates(map);
    }

    private static void propagateDeactivationRecursive(GameMap map, Position position, Set<Position> visited) {
        if (visited.contains(position)) {
            return;
        }
        visited.add(position);

        // Get cardinally adjacent positions
        List<Position> adjacentPositions = position.getCardinallyAdjacentPositions();

        for (Position adjPos : adjacentPositions) {
            List<Entity> entities = map.getEntities(adjPos);

            for (Entity entity : entities) {
                // Deactivate adjacent wires that have no other active source
                if (entity instanceof Wire wire && wire.isActivated()) {
                    // Check if this wire still has any active conductor adjacent
                    if (!hasActiveAdjacentConductor(map, adjPos)) {
                        wire.deactivate(map);
                        propagateDeactivationRecursive(map, adjPos, visited);
                    }
                }
            }
        }
    }

    private static boolean hasActiveAdjacentConductor(GameMap map, Position pos) {
        List<Position> adjacentPositions = pos.getCardinallyAdjacentPositions();

        for (Position adjPos : adjacentPositions) {
            List<Entity> entities = map.getEntities(adjPos);
            for (Entity entity : entities) {
                if (entity instanceof Conductor conductor && conductor.isActivated()
                        && !(entity instanceof Wire && ((Wire) entity).getPosition().equals(pos))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Update all logical entities on the map to check their activation states
     * @param map The game map
     */
    public static void updateAllLogicalStates(GameMap map) {
        int tick = map.getGame().getTick();

        // Update all logical entities
        List<LogicalEntity> logicalEntities = map.getEntities().stream().filter(LogicalEntity.class::isInstance)
                .map(LogicalEntity.class::cast).toList();

        for (LogicalEntity entity : logicalEntities) {
            entity.updateLogicalState(map, tick);
        }
    }
}
