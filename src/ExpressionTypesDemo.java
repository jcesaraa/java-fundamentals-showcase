/**
 * Scenario 1 - Fundamental expression types in Java.
 *
 * This class answers the guiding question of Scenario 1:
 * "What values do boolean, arithmetic, and string expressions represent?"
 *
 *   - An ARITHMETIC expression always evaluates to a NUMERIC value
 *     (an int, long, double, etc.), the result of applying operators
 *     such as +, -, *, /, % to numeric operands.
 *   - A BOOLEAN expression always evaluates to exactly one of the two
 *     logical values TRUE or FALSE, produced by relational operators
 *     (==, !=, <, >, <=, >=) or logical operators (&&, ||, !).
 *   - A STRING expression always evaluates to a sequence of characters
 *     (a String object), produced by concatenating text and/or values
 *     with the + operator, or by calling String methods.
 *
 * Each private method below is a small, self-contained demonstration.
 * Run this class directly, or call demonstrateAll() from another class.
 */
public class ExpressionTypesDemo {

    public static void demonstrateAll() {
        System.out.println("=== Scenario 1: Fundamental Expression Types ===");
        demonstrateArithmeticExpressions();
        demonstrateBooleanExpressions();
        demonstrateStringExpressions();
        System.out.println();
    }

    /**
     * Arithmetic expressions produce a numeric value.
     * This method shows operator precedence, integer vs. floating point
     * division, the modulo operator, and pre/post increment operators.
     */
    private static void demonstrateArithmeticExpressions() {
        System.out.println("\n--- Arithmetic expressions ---");

        // Operator precedence: multiplication/division bind tighter than
        // addition/subtraction, so this evaluates as 2 + (3 * 4) = 14,
        // NOT (2 + 3) * 4 = 20.
        int precedenceResult = 2 + 3 * 4;
        System.out.println("2 + 3 * 4 = " + precedenceResult + " (multiplication happens first)");

        // Integer division truncates the fractional part.
        int integerDivision = 7 / 2;      // 3, not 3.5
        int modulo = 7 % 2;                // 1 (the remainder of 7 / 2)
        System.out.println("7 / 2 = " + integerDivision + " (integer division truncates)");
        System.out.println("7 % 2 = " + modulo + " (modulo returns the remainder)");

        // Mixing an int with a double promotes the whole expression to double,
        // so the division below keeps the fractional part.
        double floatingPointDivision = 7 / 2.0;
        System.out.println("7 / 2.0 = " + floatingPointDivision + " (mixing int and double promotes to double)");

        // Pre-increment (++x) increments first, then yields the new value.
        // Post-increment (x++) yields the current value, then increments.
        int x = 5;
        int postIncrementResult = x++; // postIncrementResult = 5, x becomes 6
        int preIncrementResult = ++x;  // x becomes 7, preIncrementResult = 7
        System.out.println("x++ yielded " + postIncrementResult + ", then ++x yielded " + preIncrementResult
                + " (final x = " + x + ")");
    }

    /**
     * Boolean expressions always produce either true or false.
     * This method shows relational operators, logical operators,
     * and short-circuit evaluation.
     */
    private static void demonstrateBooleanExpressions() {
        System.out.println("\n--- Boolean expressions ---");

        int age = 20;
        int minimumAge = 18;

        // A relational operator compares two values and yields a boolean.
        boolean isAdult = age >= minimumAge;
        System.out.println("age >= minimumAge => " + isAdult);

        boolean hasStudentDiscount = true;
        // Logical AND (&&) is true only when both operands are true.
        boolean qualifiesForDiscountedAdultTicket = isAdult && hasStudentDiscount;
        System.out.println("isAdult && hasStudentDiscount => " + qualifiesForDiscountedAdultTicket);

        // Short-circuit evaluation: with &&, if the left side is false the
        // right side is never evaluated (it is "short-circuited"). This is
        // heavily used to avoid errors, e.g. checking an array is not empty
        // before reading its first element.
        int[] numbers = {};
        boolean isFirstElementPositive = numbers.length > 0 && numbers[0] > 0;
        System.out.println("numbers.length > 0 && numbers[0] > 0 => " + isFirstElementPositive
                + " (numbers[0] was never evaluated, so no ArrayIndexOutOfBoundsException was thrown)");

        // Logical NOT (!) inverts a boolean value.
        boolean isNotAdult = !isAdult;
        System.out.println("!isAdult => " + isNotAdult);
    }

    /**
     * String expressions always produce a String (a sequence of characters).
     * This method shows concatenation and a common source of confusion:
     * the + operator behaves differently depending on operand types and
     * evaluation order (left to right).
     */
    private static void demonstrateStringExpressions() {
        System.out.println("\n--- String expressions ---");

        String firstName = "Ada";
        String lastName = "Lovelace";
        // Concatenation joins operands into a single String.
        String fullName = firstName + " " + lastName;
        System.out.println("firstName + \" \" + lastName = \"" + fullName + "\"");

        // + is evaluated left to right. Once a String appears, every
        // following + becomes string concatenation instead of arithmetic.
        String mixedLeftToRight = "Result: " + 1 + 2; // "Result: " + 1 -> "Result: 1", then + 2 -> "Result: 12"
        String mixedWithParentheses = "Result: " + (1 + 2); // (1 + 2) is evaluated first as arithmetic -> 3
        System.out.println("\"Result: \" + 1 + 2 = \"" + mixedLeftToRight + "\"");
        System.out.println("\"Result: \" + (1 + 2) = \"" + mixedWithParentheses + "\"");

        // Comparing string content must use .equals(), not ==, because ==
        // compares object references, not the characters inside the String.
        String greeting = "hello";
        boolean sameContent = greeting.equals("hello");
        System.out.println("greeting.equals(\"hello\") => " + sameContent);
    }

    public static void main(String[] args) {
        demonstrateAll();
    }
}
