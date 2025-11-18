package dungeonmania.entities.logical;

import dungeonmania.map.GameMap;

/**
 * Interface for entities that can conduct electrical current.
 * This includes wires and activated switches.
 */
public interface Conductor {
    /**
     * Check if this conductor is currently activated (carrying current)
     * @return true if activated, false otherwise
     */
    boolean isActivated();

    /**
     * Activate this conductor, causing it to carry current
     * @param map The game map
     * @param tick The tick when activation occurred (for CO_AND logic)
     */
    void activate(GameMap map, int tick);

    /**
     * Deactivate this conductor, stopping current flow
     * @param map The game map
     */
    void deactivate(GameMap map);
}
