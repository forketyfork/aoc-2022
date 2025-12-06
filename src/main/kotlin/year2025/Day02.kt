package year2025

import utils.isEven

class Day02 {

    fun part1(input: String): Long = parse(input).flatMap { it.findInvalidIdsPart1() }.sum()
    fun part2(input: String): Long = parse(input).flatMap { it.findInvalidIdsPart2() }.sum()

    fun parse(input: String) = input.split(",").map {
        val (from, to) = it.split("-").map(String::toLong)
        from..to
    }

    private fun LongRange.findInvalidIdsPart1() = filter {
        val string = it.toString()
        val len = string.length shr 1
        string.length.isEven() && string.take(len) == string.takeLast(len)
    }

    private fun LongRange.findInvalidIdsPart2() = filter {
        val string = it.toString()
        (1..string.length / 2).any { len ->
            string.length % len == 0 && string.chunkedSequence(len)
                .windowed(2)
                .all { (first, second) -> first == second }
        }
    }
}
