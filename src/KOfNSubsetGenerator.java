/**
 * Applied problem for Scenario 2 (and connected to Scenario 1).
 *
 * Motivation (taken almost literally from the Scenario 2 introduction):
 * "In a game, show every possible way a player can pick K items out of
 * a set of N available items."
 *
 * This is a classic example of representing a data SET with a single
 * integer value: since a Java int has 32 bits, we can use bit number i
 * (0-indexed) to mean "item i is included in this subset". An integer
 * from 0 to (2^N - 1) then represents one specific subset of the N
 * items, and looping over that whole range enumerates EVERY possible
 * subset. Filtering by "how many bits are set" (its population count)
 * gives exactly the subsets of size K. This technique only needs N to
 * be small enough to fit in an int (N <= 30 here, to stay well within
 * the 32-bit range and leave room for the sign bit), which is a
 * reasonable assumption for a small game inventory.
 *
 * Control structures used: a for loop enumerates all candidate masks,
 * an if selects the ones with exactly K bits set, and a while loop
 * (inside countSetBits) implements Brian Kernighan's bit-counting
 * algorithm.
 */
public class KOfNSubsetGenerator {

    /**
     * Prints every subset of exactly {@code k} items chosen from
     * {@code items}, one subset per line.
     *
     * @param items the full set of available items (its size is N)
     * @param k     the exact subset size to look for (0 <= k <= items.length)
     */
    public static void printAllSubsetsOfSize(String[] items, int k) {
        int n = items.length;
        if (n > 30) {
            // Guard against overflow: 1 << n would no longer fit safely in an int.
            throw new IllegalArgumentException("This bitmask technique only supports up to 30 items.");
        }
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("k must be between 0 and the number of items.");
        }

        int totalMasks = 1 << n; // 2^n possible subsets, including the empty one
        int foundCount = 0;

        // Every integer from 0 to totalMasks - 1 is one candidate subset.
        for (int mask = 0; mask < totalMasks; mask++) {
            if (countSetBits(mask) == k) {
                System.out.println("  " + describeSubset(items, mask));
                foundCount++;
            }
        }

        System.out.println("Total subsets of size " + k + " out of " + n + " items: " + foundCount);
    }

    /**
     * Counts how many bits of {@code mask} are set to 1, using Brian
     * Kernighan's algorithm: {@code mask & (mask - 1)} clears the
     * lowest set bit on every iteration, so the loop runs exactly once
     * per set bit instead of once per bit position.
     */
    private static int countSetBits(int mask) {
        int count = 0;
        while (mask != 0) {
            mask = mask & (mask - 1); // clear the lowest set bit
            count++;
        }
        return count;
    }

    /**
     * Builds a human-readable description of the items selected by
     * {@code mask}, e.g. "{ Sword, Shield }".
     */
    private static String describeSubset(String[] items, int mask) {
        StringBuilder description = new StringBuilder("{ ");
        boolean isFirstItem = true;
        for (int bitPosition = 0; bitPosition < items.length; bitPosition++) {
            // (mask >> bitPosition) & 1 isolates a single bit: it is 1
            // when "bitPosition" is included in this subset.
            boolean isItemIncluded = ((mask >> bitPosition) & 1) == 1;
            if (isItemIncluded) {
                if (!isFirstItem) {
                    description.append(", ");
                }
                description.append(items[bitPosition]);
                isFirstItem = false;
            }
        }
        description.append(" }");
        return description.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Applied example: all K-of-N subsets using an int as a bitmask ===");
        String[] gameInventory = {"Sword", "Shield", "Potion", "Bow"};
        int itemsToPick = 2;

        System.out.println("Every way to pick " + itemsToPick + " item(s) out of " + gameInventory.length + ":");
        printAllSubsetsOfSize(gameInventory, itemsToPick);
    }
}
