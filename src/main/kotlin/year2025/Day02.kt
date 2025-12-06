package year2025

import utils.isEven

class Day02 {

    fun part1(input: String): Long = parse(input).flatMap { it.findInvalidIds() }.sum()

    fun part2(input: String): Long {
        return 0L
    }

    fun parse(input: String) = input.split(",").map {
        val (from, to) = it.split("-").map { value -> value.toLong() }
        from..to
    }

    private fun LongRange.findInvalidIds(): List<Long> {
        return filter {
            val string = it.toString()
            string.length.isEven()
                    && string.take(string.length / 2) == string.substring(string.length / 2)
        }
    }
}