import java.util.Scanner;

/**
 * Exercise 10 from the "Problemas propuestos" (proposed problems) section
 * of Lectura Fundamental 1 (Unit 1, Scenario 1 - "Conceptos Fundamentales
 * de Programación", Politécnico Grancolombiano): "Cantidad de baldosas"
 * ("Number of tiles").
 *
 * Problem statement (summarized from the source material):
 * Given square tiles of side X (in meters) and a rectangular surface of
 * N by M meters, tiles cannot be cut and cannot be placed on top of one
 * another, although a tile MAY stick out past the surface's edge. What
 * is the minimum number of tiles needed to fully cover the surface?
 *
 * Input (standard input): three positive integers, in this exact order:
 *   X N M
 * Output (standard output): a single integer — the minimum number of
 * tiles needed.
 *
 * Constraint imposed by the exercise itself: the solution must NOT use
 * conditionals (if / switch / the ternary operator ?:), loops (for /
 * while / do-while), or any method from java.lang.Math.
 *
 * Approach: starting from one corner of the rectangle and laying whole
 * tiles side by side, the number of tiles needed to cover a single
 * dimension L with tiles of side X is the ceiling of (L / X): one whole
 * tile per full X meters, plus one extra (partially used) tile whenever
 * L is not an exact multiple of X. The total tile count is simply the
 * product of the tiles needed along the width and along the height.
 * This grid arrangement is provably optimal: since no two tiles may
 * overlap, no arrangement can cover more than X meters of any given row
 * or column per tile, so the same ceiling count is also a lower bound
 * for ANY valid (non-overlapping) placement, not just this one.
 *
 * Because Math.ceil() is disallowed, the ceiling of an integer division
 * a / b (for positive a, b) is computed here with pure integer
 * arithmetic as (a + b - 1) / b, relying on Java's truncating integer
 * division. This needs no if, no loop, and no java.lang.Math call.
 *
 * Worked examples from the source material and this program's output:
 *   X=3  N=16 M=23 -> 48  (matches the source material)
 *   X=5  N=4  M=3  -> 1   (matches the source material)
 *   X=5  N=10 M=30 -> 12  (source material prints 6; see
 *                          TilesCoverageExamplesCheck.java for why that
 *                          printed value is not physically achievable)
 *   X=5  N=11 M=30 -> 18  (source material prints 9, same discrepancy)
 *   X=5  N=10 M=31 -> 14  (source material prints 8, same discrepancy)
 */
public class TilesCoverageCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tileSide = scanner.nextInt();       // X: side of each square tile, in meters
        int surfaceWidth = scanner.nextInt();   // N: one side of the rectangular surface
        int surfaceHeight = scanner.nextInt();  // M: the other side of the rectangular surface

        int tilesAlongWidth = ceilingDivision(surfaceWidth, tileSide);
        int tilesAlongHeight = ceilingDivision(surfaceHeight, tileSide);
        int minimumTileCount = tilesAlongWidth * tilesAlongHeight;

        System.out.println(minimumTileCount);

        scanner.close();
    }

    /**
     * Returns the ceiling of {@code numerator / denominator} for two
     * positive integers, using only arithmetic operators (no
     * conditionals, no loops, no java.lang.Math), as required by the
     * exercise statement.
     */
    private static int ceilingDivision(int numerator, int denominator) {
        return (numerator + denominator - 1) / denominator;
    }
}
