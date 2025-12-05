package year2025

class Day01 {

    fun part1(input: String): Int {
        return input.lines().map {
            it.drop(1).toLong() * if (it.first() == 'L') {
                -1
            } else {
                1
            }
        }.runningFold(50L) { acc, v ->
            ((100 + acc + v) % 100)
        }.count { it == 0L }
    }

    fun part2(input: String): Long {
        return 0L
    }

}
