package dungeonmania.goals;

import dungeonmania.Game;

/**
 * Base interface for all goals using the Composite Pattern.
 * This allows for easy extension of new goal types without modifying existing code.
 */
public interface Goal {
    /**
     * Check if this goal has been achieved
     * @param game The current game state
     * @return true if the goal has been achieved, false otherwise
     */
    boolean achieved(Game game);

    /**
     * Get a string representation of this goal
     * @param game The current game state
     * @return String representation of the goal, or empty string if achieved
     */
    String toString(Game game);
}
