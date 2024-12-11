package year2024

class Day11 {

    fun part1(input: String) = solve2(input, 25)

    fun part2(input: String) = solve2(input, 75)

    fun solve2(input: String, iterations: Int): Long {
        val memo = mutableMapOf<Pair<Long, Int>, Long>()
        return input.split(' ').map { it.toLong() }.sumOf { stone ->
            memo.numOfStones(stone, iterations)
        }
    }

    fun MutableMap<Pair<Long, Int>, Long>.numOfStones(stone: Long, iterations: Int): Long {
        var value = get(stone to iterations)
        if (value == null) {
            value = if (iterations == 0) {
                1L
            } else if (stone == 0L) {
                numOfStones(1L, iterations - 1)
            } else {
                val str = stone.toString()
                if (str.length % 2 == 0) {
                    val (one, two) = str.chunked(str.length / 2)
                    numOfStones(one.toLong(), iterations - 1) + numOfStones(two.toLong(), iterations - 1)
                } else {
                    numOfStones(2024 * stone, iterations - 1)
                }
            }

            put(stone to iterations, value)
        }
        return value
    }

}