/**
 * Documentation / verification helper for {@link TilesCoverageCalculator}
 * (Exercise 10, "Cantidad de baldosas", Lectura Fundamental 1).
 *
 * NOTE: unlike TilesCoverageCalculator.java (the actual exercise
 * solution, which must avoid conditionals, loops, and java.lang.Math),
 * this class is only a supporting explanation and is free to use normal
 * Java constructs such as a for loop and the ternary operator.
 *
 * It replays every worked example printed in the source material
 * (Lectura_Fundamental_1.pdf, page 27-28) against the same
 * ceiling-division formula used by the real solution, and documents a
 * discrepancy found in three of them.
 *
 * Examples 1 and 2 match this program's computed output exactly.
 * Examples 3, 4 and 5, however, print expected results (6, 9, 8) that
 * are not physically achievable: a square tile has an area of
 * tileSide * tileSide, so a given number of non-overlapping tiles can
 * cover at most that many times tileSide^2 square meters. For example 3
 * (X=5, N=10, M=30) the surface area is 300 m^2, so covering it without
 * overlap needs at least 300 / 25 = 12 tiles; six tiles could cover at
 * most 150 m^2, less than half the surface. The same reasoning rules
 * out the printed values for examples 4 and 5. This program therefore
 * treats those three printed values as a transcription error in the PDF
 * and reports both the printed value and the mathematically correct one
 * for each example.
 */
public class TilesCoverageExamplesCheck {

    /** One worked example: inputs plus the value printed in the source PDF. */
    private static final class Example {
        final int tileSide;
        final int surfaceWidth;
        final int surfaceHeight;
        final int valuePrintedInSource;

        Example(int tileSide, int surfaceWidth, int surfaceHeight, int valuePrintedInSource) {
            this.tileSide = tileSide;
            this.surfaceWidth = surfaceWidth;
            this.surfaceHeight = surfaceHeight;
            this.valuePrintedInSource = valuePrintedInSource;
        }
    }

    public static void main(String[] args) {
        Example[] examplesFromSourceMaterial = {
            new Example(3, 16, 23, 48),
            new Example(5, 4, 3, 1),
            new Example(5, 10, 30, 6),
            new Example(5, 11, 30, 9),
            new Example(5, 10, 31, 8),
        };

        for (Example example : examplesFromSourceMaterial) {
            int tilesAlongWidth = ceilingDivision(example.surfaceWidth, example.tileSide);
            int tilesAlongHeight = ceilingDivision(example.surfaceHeight, example.tileSide);
            int computedTileCount = tilesAlongWidth * tilesAlongHeight;

            int surfaceArea = example.surfaceWidth * example.surfaceHeight;
            int tileArea = example.tileSide * example.tileSide;
            int minimumPossibleTilesByArea = ceilingDivision(surfaceArea, tileArea);

            boolean printedValueIsPhysicallyPossible = example.valuePrintedInSource >= minimumPossibleTilesByArea;
            String verdict = printedValueIsPhysicallyPossible
                    ? "matches this program's computed answer"
                    : "impossible by area alone (needs at least " + minimumPossibleTilesByArea + " tiles) -> likely a PDF typo";

            System.out.println("X=" + example.tileSide + " N=" + example.surfaceWidth + " M=" + example.surfaceHeight
                    + " | computed=" + computedTileCount
                    + " | printed in source=" + example.valuePrintedInSource
                    + " | " + verdict);
        }
    }

    private static int ceilingDivision(int numerator, int denominator) {
        return (numerator + denominator - 1) / denominator;
    }
}
