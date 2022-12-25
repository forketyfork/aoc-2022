package year2022


class Day02 {

    enum class Move(private val points: Int) {

        ROCK(1), PAPER(2), SCISSORS(3);

        fun score(other: Move): Int = when {
            this == other -> Outcome.DRAW
            this.ordinal == (other.ordinal + 1) % Move.values().size -> Outcome.WIN
            else -> Outcome.LOSE
        }.points + this.points

        fun loser() = Move.values()[(Move.values().size + this.ordinal - 1) % Move.values().size]
        fun winner() = Move.values()[(this.ordinal + 1) % Move.values().size]

        companion object {
            fun ofChar(c: Char): Move = when (c) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> error("Unknown move: $c")
            }
        }
    }

    enum class Outcome(val code: Char, val points: Int) {

        LOSE('X', 0), DRAW('Y', 3), WIN('Z', 6);

        companion object {
            fun ofChar(c: Char): Outcome = values().find { it.code == c } ?: error("Unknown outcome: $c")
        }
    }

    private fun calculateScore(input: List<String>, parser: (Pair<Char, Char>) -> Pair<Move, Move>): Int =
        input.map { it.split(" ").map(String::first) }
            .map { (p1, p2) -> p1 to p2 }
            .map(parser)
            .sumOf { (move1, move2) -> move2.score(move1) }

    fun part1(input: String) = calculateScore(input.lines()) {
        Move.ofChar(it.first) to Move.ofChar(it.second)
    }

    fun part2(input: String) = calculateScore(input.lines()) {
        val move1 = Move.ofChar(it.first)
        val outcome = Outcome.ofChar(it.second)
        val move2 =
            if (outcome == Outcome.LOSE) move1.loser() else if (outcome == Outcome.DRAW) move1 else move1.winner()
        move1 to move2
    }

}
