# Java Fundamentals Showcase — Scenarios 1 & 2

A small, self-contained Java project written for the *Fundamentos de Programación*
module (Mobile Application Development program, Politécnico Grancolombiano). It
is a documented solution / walkthrough for **Scenario 1** (fundamental
expression types) and **Scenario 2** (program flow control structures),
including one applied problem that ties both scenarios together, plus a
worked solution to **Exercise 10** ("Cantidad de baldosas") from the
"Problemas propuestos" section of *Lectura Fundamental 1*.

No external libraries are required — only the JDK.

## How to run

```bash
cd src
javac *.java
java Main
```

This compiles every class and runs the Scenario 1 / Scenario 2 demonstrations
in sequence, printing labeled output for each concept to the console.

`TilesCoverageCalculator` (Exercise 10) is a separate, standalone console
program — it reads its own input, so run it on its own:

```bash
cd src
javac TilesCoverageCalculator.java TilesCoverageExamplesCheck.java
echo "3 16 23" | java TilesCoverageCalculator   # -> 48
java TilesCoverageExamplesCheck                 # replays every worked example from the PDF
```

## Project contents

| File | Scenario | What it covers |
|---|---|---|
| `ExpressionTypesDemo.java` | 1 | Arithmetic, boolean, and string expressions: operator precedence, integer vs. floating-point division, increment operators, relational/logical operators, short-circuit evaluation, and the classic `+` concatenation-vs-arithmetic pitfall. |
| `ControlFlowShowcase.java` | 2 | One minimal, clearly labeled example of each control-flow statement requested by the scenario: `if`, `if-else`, `switch`, `for`, `while`, `do-while`. |
| `KOfNSubsetGenerator.java` | 2 (applied) | Solves the scenario's own motivating example — *"show every way a player can pick K items out of a set of N items in a game"* — using a single `int` as a bitmask to represent a subset. |
| `TilesCoverageCalculator.java` | Lectura Fundamental 1, Exercise 10 | Reads `X N M` from standard input and prints the minimum number of `X`×`X` tiles needed to cover an `N`×`M` surface, without using `if`, loops, or `java.lang.Math` (as the exercise requires). |
| `TilesCoverageExamplesCheck.java` | Lectura Fundamental 1, Exercise 10 (supporting) | Replays all five worked examples from the PDF and documents a discrepancy found in three of them (see below). Free to use normal Java constructs, since it is not the exercise solution itself. |
| `Main.java` | — | Entry point that runs the Scenario 1 and Scenario 2 demonstrations above in order. |

## Scenario 1 — answering the guiding question

*"What values do boolean, arithmetic, and string expressions represent?"*

- An **arithmetic** expression always evaluates to a **numeric** value
  (`int`, `double`, etc.).
- A **boolean** expression always evaluates to exactly one of the two
  logical values **true** or **false**.
- A **string** expression always evaluates to a **sequence of characters**
  (a `String` object).

`ExpressionTypesDemo.java` demonstrates each case with runnable examples and
inline comments explaining *why* the output is what it is (for example, why
`"Result: " + 1 + 2` prints `Result: 12` while `"Result: " + (1 + 2)` prints
`Result: 3`).

## Scenario 2 — the applied problem

Scenario 2 opens with this motivating example (paraphrased): *a game needs to
show every possible way a player can acquire K items out of a set of N
available items.* `KOfNSubsetGenerator.java` solves exactly this, using the
technique the scenario's own introduction hints at: **representing a data set
with an integer**.

Since a Java `int` has 32 bits, bit number *i* can mean "item *i* is included
in the subset". Every integer from `0` to `2^N - 1` therefore represents one
specific subset of the N items, so a `for` loop over that whole range
enumerates *every* possible subset. An `if` filters the ones whose number of
set bits (its *population count*) equals K, and a `while` loop implements
Brian Kernighan's classic bit-counting algorithm (`mask & (mask - 1)` clears
the lowest set bit on each iteration).

Example run (`N = 4` items — `Sword, Shield, Potion, Bow` —, `K = 2`):

```
Every way to pick 2 item(s) out of 4:
  { Sword, Shield }
  { Sword, Potion }
  { Shield, Potion }
  { Sword, Bow }
  { Shield, Bow }
  { Potion, Bow }
Total subsets of size 2 out of 4 items: 6
```

This matches the expected combinatorial result, C(4, 2) = 6.

*"What good practices should a developer keep in mind?"* — see the Javadoc
comment above `demonstrateBestPractices()` in `ControlFlowShowcase.java` for
the full answer; in short: meaningful names, small single-purpose methods,
choosing the control structure that best expresses intent, commenting the
*why*, validating inputs/edge cases, and consistent formatting. This project
tries to follow all of them throughout.

## Lectura Fundamental 1, Exercise 10 — "Cantidad de baldosas"

The exercise: given square tiles of side `X` meters and a rectangular
surface of `N` by `M` meters, tiles cannot be cut and cannot overlap
(though a tile may stick out past the surface's edge). What is the
minimum number of tiles needed to fully cover the surface? The exercise
requires reading `X N M` (in that order) from standard input, printing a
single integer, and forbids `if`/`switch`/`?:`, any loop, and any
`java.lang.Math` method.

**Approach.** Laying whole tiles from one corner, the tiles needed along a
single dimension `L` is the ceiling of `L / X`. The total is the product
of the tiles needed along each dimension. Because no two tiles may
overlap, this grid count is also a lower bound for *any* valid placement,
so it is provably optimal — not just *a* solution, but *the* minimum.
Since `Math.ceil()` is banned, the ceiling of a positive integer division
is computed with plain arithmetic: `(a + b - 1) / b`, which needs no `if`,
no loop, and no `Math` call.

**A discrepancy in the source PDF.** Of the five worked examples printed
in *Lectura_Fundamental_1.pdf* (page 27-28), the first two match this
program's output exactly:

| X | N | M | PDF says | This program |
|---|---|---|---|---|
| 3 | 16 | 23 | 48 | 48 |
| 5 | 4 | 3 | 1 | 1 |
| 5 | 10 | 30 | 6 | 12 |
| 5 | 11 | 30 | 9 | 18 |
| 5 | 10 | 31 | 8 | 14 |

The last three printed values are not physically possible: a tile covers
`X²` square meters, so covering an `N×M` surface without overlap needs at
least `⌈(N·M)/X²⌉` tiles regardless of arrangement. For `X=5, N=10, M=30`
that lower bound alone is `⌈300/25⌉ = 12`, yet the PDF prints `6` — six
tiles could cover at most `150 m²`, less than half the surface. The same
check rules out `9` and `8` for the other two rows. `TilesCoverageExamplesCheck.java`
runs this exact check programmatically for all five rows. This looks like
a transcription error in the source material rather than a different
intended problem, since the general statement, the formula, and the
*first two* examples are all self-consistent with the ceiling-product
formula above.

## Originality note

This code was written from scratch for this module's forum activity; it does
not copy any existing repository or tutorial. The bitmask-subset technique
itself is a well-known, textbook computer-science idea (not specific to any
source), applied here to the scenario's own game-inventory example.

## License

MIT — feel free to reuse any part of this for the group project.
