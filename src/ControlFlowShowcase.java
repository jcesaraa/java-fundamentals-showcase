/**
 * Scenario 2 - Program flow control structures.
 *
 * This class showcases the six control-flow statements requested by
 * Scenario 2's competency indicators: if, if-else, switch, for, while
 * and do-while. Each one lives in its own small, clearly labeled method
 * so it can be studied in isolation.
 *
 * It also answers, in the comment above demonstrateBestPractices(), the
 * guiding question of Scenario 2: "What good practices should a
 * developer keep in mind?"
 */
public class ControlFlowShowcase {

    public static void demonstrateAll() {
        System.out.println("=== Scenario 2: Control Flow Structures ===");
        demonstrateIf();
        demonstrateIfElse();
        demonstrateSwitch();
        demonstrateFor();
        demonstrateWhile();
        demonstrateDoWhile();
        demonstrateBestPractices();
        System.out.println();
    }

    /** A plain "if" only runs its block when the condition is true. */
    private static void demonstrateIf() {
        System.out.println("\n--- if ---");
        int temperatureCelsius = 38;
        if (temperatureCelsius > 37) {
            System.out.println(temperatureCelsius + "C is above normal body temperature.");
        }
    }

    /** "if-else" picks exactly one of two branches. */
    private static void demonstrateIfElse() {
        System.out.println("\n--- if-else ---");
        int score = 65;
        if (score >= 60) {
            System.out.println("Score " + score + ": passed.");
        } else {
            System.out.println("Score " + score + ": failed.");
        }
    }

    /**
     * "switch" is a cleaner alternative to a long if-else chain when
     * comparing one variable against several constant values.
     */
    private static void demonstrateSwitch() {
        System.out.println("\n--- switch ---");
        int dayOfWeek = 6; // 1 = Monday ... 7 = Sunday
        String dayType;
        switch (dayOfWeek) {
            case 6:
            case 7:
                dayType = "weekend";
                break;
            default:
                dayType = "weekday";
                break;
        }
        System.out.println("Day " + dayOfWeek + " is a " + dayType + ".");
    }

    /** "for" is the natural choice when the number of iterations is known in advance. */
    private static void demonstrateFor() {
        System.out.println("\n--- for ---");
        int sumOfFirstFiveSquares = 0;
        for (int i = 1; i <= 5; i++) {
            sumOfFirstFiveSquares += i * i;
        }
        System.out.println("Sum of squares from 1 to 5 = " + sumOfFirstFiveSquares);
    }

    /** "while" checks its condition before every iteration, including the first one. */
    private static void demonstrateWhile() {
        System.out.println("\n--- while ---");
        int value = 100;
        int divisionsByTwo = 0;
        while (value > 1) {
            value /= 2;
            divisionsByTwo++;
        }
        System.out.println("100 can be halved " + divisionsByTwo + " times before reaching 1 or less.");
    }

    /**
     * "do-while" always runs its block at least once, then checks the
     * condition. It is the right tool when a task must happen before
     * the loop can even evaluate whether to repeat, such as validating
     * a value that has not been read yet.
     */
    private static void demonstrateDoWhile() {
        System.out.println("\n--- do-while ---");
        // Simulated user input attempts (in a real program these would
        // come from a Scanner). The loop keeps "asking" until it finds
        // a valid value, but it always evaluates at least one attempt.
        int[] simulatedInputAttempts = {-5, 0, 12};
        int attemptIndex = 0;
        int validatedValue;
        do {
            validatedValue = simulatedInputAttempts[attemptIndex];
            attemptIndex++;
        } while (validatedValue <= 0 && attemptIndex < simulatedInputAttempts.length);
        System.out.println("First valid (positive) simulated input found: " + validatedValue);
    }

    /**
     * Good practices a developer should keep in mind (Scenario 2's guiding
     * question), applied consistently throughout this whole project:
     *   1. Use meaningful, self-explanatory names for variables, methods
     *      and classes instead of single letters or abbreviations.
     *   2. Keep methods short and focused on a single responsibility.
     *   3. Prefer the control structure that best expresses the intent
     *      (e.g. "for" for a known number of repetitions, "while" when
     *      the count is unknown, "do-while" when the body must run at
     *      least once, "switch" instead of long if-else chains).
     *   4. Comment the "why", not just the "what", and keep comments in
     *      sync with the code.
     *   5. Validate inputs and edge cases (e.g. empty collections, zero,
     *      negative numbers) before operating on them.
     *   6. Keep indentation and formatting consistent to make control
     *      flow easy to follow visually.
     */
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Good practices ---");
        System.out.println("See the Javadoc comment above demonstrateBestPractices() in the source code.");
    }

    public static void main(String[] args) {
        demonstrateAll();
    }
}
