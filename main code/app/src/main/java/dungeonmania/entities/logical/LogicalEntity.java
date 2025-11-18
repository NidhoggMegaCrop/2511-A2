package dungeonmania.entities.logical;

import dungeonmania.map.GameMap;

/**
 * Interface for entities that have logical activation conditions.
 * These entities activate based on logical rules (AND, OR, XOR, CO_AND).
 */
public interface LogicalEntity {
    /**
     * Get the logic rule for this entity
     * @return The logic rule (AND, OR, XOR, CO_AND)
     */
    LogicRule getLogicRule();

    /**
     * Check if this logical entity is currently activated
     * @return true if activated, false otherwise
     */
    boolean isLogicallyActivated();

    /**
     * Update the activation state based on adjacent conductors
     * @param map The game map
     * @param tick The current game tick
     */
    void updateLogicalState(GameMap map, int tick);

    /**
     * Activate this logical entity
     * @param map The game map
     */
    void activateLogical(GameMap map);

    /**
     * Deactivate this logical entity
     * @param map The game map
     */
    void deactivateLogical(GameMap map);
}
