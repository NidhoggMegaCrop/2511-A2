package dungeonmania.entities.enemies.movement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.util.Position;

/**
 * Strategy interface for enemy movement behavior.
 * Different enemies can use different movement strategies that can be swapped at runtime.
 */
public interface MovementStrategy {
    /**
     * Calculate the next position for the enemy to move to
     * @param game Current game state
     * @param enemy The enemy that is moving
     * @return The next position to move to
     */
    Position getNextPosition(Game game, Enemy enemy);
}
