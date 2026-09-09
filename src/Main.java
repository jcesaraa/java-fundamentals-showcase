/**
 * Entry point that runs the three demonstrations in order:
 * Scenario 1 (expression types), Scenario 2 (control flow structures),
 * and the applied K-of-N subset generator that ties both scenarios
 * together (arithmetic/bitwise expressions driving a for/while loop).
 */
public class Main {
    public static void main(String[] args) {
        ExpressionTypesDemo.demonstrateAll();
        ControlFlowShowcase.demonstrateAll();

        System.out.println("=== Applied example: all K-of-N subsets using an int as a bitmask ===");
        String[] gameInventory = {"Sword", "Shield", "Potion", "Bow"};
        int itemsToPick = 2;
        System.out.println("Every way to pick " + itemsToPick + " item(s) out of " + gameInventory.length + ":");
        KOfNSubsetGenerator.printAllSubsetsOfSize(gameInventory, itemsToPick);
    }
}
