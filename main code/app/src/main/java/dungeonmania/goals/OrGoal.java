package dungeonmania.goals;

import dungeonmania.Game;

/**
 * Composite goal representing a logical OR of two sub-goals
 * At least one sub-goal must be achieved for this goal to be achieved
 */
public class OrGoal implements Goal {
    private final Goal goal1;
    private final Goal goal2;

    /**
     * Create an OR goal from two sub-goals
     * @param goal1 First sub-goal
     * @param goal2 Second sub-goal
     */
    public OrGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        return goal1.achieved(game) || goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (achieved(game)) {
            return "";
        }
        return "(" + goal1.toString(game) + " OR " + goal2.toString(game) + ")";
    }

    public Goal getGoal1() {
        return goal1;
    }

    public Goal getGoal2() {
        return goal2;
    }
}
