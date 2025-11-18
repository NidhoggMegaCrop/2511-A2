package dungeonmania.goals;

import dungeonmania.Game;

/**
 * Composite goal representing a logical AND of two sub-goals
 * Both sub-goals must be achieved for this goal to be achieved
 */
public class AndGoal implements Goal {
    private final Goal goal1;
    private final Goal goal2;

    /**
     * Create an AND goal from two sub-goals
     * @param goal1 First sub-goal
     * @param goal2 Second sub-goal
     */
    public AndGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        return goal1.achieved(game) && goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (achieved(game)) {
            return "";
        }
        return "(" + goal1.toString(game) + " AND " + goal2.toString(game) + ")";
    }

    public Goal getGoal1() {
        return goal1;
    }

    public Goal getGoal2() {
        return goal2;
    }
}
