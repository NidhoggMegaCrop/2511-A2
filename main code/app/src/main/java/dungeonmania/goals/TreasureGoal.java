package dungeonmania.goals;

import dungeonmania.Game;

/**
 * Concrete goal representing the requirement to collect a certain amount of treasure
 */
public class TreasureGoal implements Goal {
    private final int target;

    /**
     * Create a treasure goal with the specified target
     * @param target The number of treasure pieces that must be collected
     */
    public TreasureGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null) {
            return false;
        }

        return game.getCollectedTreasureCount() >= target;
    }

    @Override
    public String toString(Game game) {
        return achieved(game) ? "" : ":treasure";
    }

    public int getTarget() {
        return target;
    }
}
