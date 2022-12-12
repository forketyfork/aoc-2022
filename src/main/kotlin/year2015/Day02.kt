package year2015

class Day02 {

    private fun String.parse(): List<List<Int>> = lines().map { line ->
        line.split("x").map { it.toInt() }
    }

    fun part1(input: String) = input.parse()
        .map { (l, w, h) -> listOf(l * w, w * h, h * l) }
        .sumOf { list -> 2 * list.sum() + list.min() }

    fun part2(input: String) = input.parse()
        .sumOf { (l, w, h) -> 2 * (l + w + h - maxOf(l, w, h)) + l * w * h }

}