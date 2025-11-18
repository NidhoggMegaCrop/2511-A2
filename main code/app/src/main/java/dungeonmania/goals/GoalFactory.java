package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Factory class for creating Goal objects from JSON configuration.
 * Now uses the Composite Pattern with polymorphic goal types.
 */
public class GoalFactory {
    /**
     * Create a Goal object from JSON configuration
     * @param jsonGoal JSON object containing goal configuration
     * @param config Game configuration JSON object
     * @return A Goal object representing the specified goal
     */
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        String goalType = jsonGoal.getString("goal");

        return switch (goalType) {
        case "AND" -> createAndGoal(jsonGoal, config);
        case "OR" -> createOrGoal(jsonGoal, config);
        case "exit" -> new ExitGoal();
        case "boulders" -> new BouldersGoal();
        case "treasure" -> createTreasureGoal(config);
        default -> throw new IllegalArgumentException("Unknown goal type: " + goalType);
        };
    }

    /**
     * Create an AND composite goal
     */
    private static Goal createAndGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals = jsonGoal.getJSONArray("subgoals");
        Goal goal1 = createGoal(subgoals.getJSONObject(0), config);
        Goal goal2 = createGoal(subgoals.getJSONObject(1), config);
        return new AndGoal(goal1, goal2);
    }

    /**
     * Create an OR composite goal
     */
    private static Goal createOrGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals = jsonGoal.getJSONArray("subgoals");
        Goal goal1 = createGoal(subgoals.getJSONObject(0), config);
        Goal goal2 = createGoal(subgoals.getJSONObject(1), config);
        return new OrGoal(goal1, goal2);
    }

    /**
     * Create a treasure goal with the target from config
     */
    private static Goal createTreasureGoal(JSONObject config) {
        int treasureGoal = config.optInt("treasure_goal", 1);
        return new TreasureGoal(treasureGoal);
    }
}
