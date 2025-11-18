package dungeonmania.entities.logical;

/**
 * Enum representing the different logical rules that can be applied to logical entities.
 */
public enum LogicRule {
    /**
     * OR - Entity activates if 1 or more adjacent conductors are activated
     */
    OR,

    /**
     * AND - Entity activates if ALL adjacent conductors are activated
     * (must have at least 2 adjacent conductors)
     */
    AND,

    /**
     * XOR - Entity activates if exactly 1 adjacent conductor is activated
     */
    XOR,

    /**
     * CO_AND - Entity activates if 2 or more adjacent conductors are activated
     * on the same tick
     */
    CO_AND;

    /**
     * Parse a logic rule from a string
     * @param logic The logic string (e.g., "and", "or", "xor", "co_and")
     * @return The corresponding LogicRule
     * @throws IllegalArgumentException if the logic string is invalid
     */
    public static LogicRule fromString(String logic) {
        if (logic == null) {
            return null;
        }

        return switch (logic.toLowerCase()) {
        case "or" -> OR;
        case "and" -> AND;
        case "xor" -> XOR;
        case "co_and" -> CO_AND;
        default -> throw new IllegalArgumentException("Unknown logic type: " + logic);
        };
    }
}
