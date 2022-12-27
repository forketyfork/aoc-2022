package year2017

class Day02 {

    fun part1(input: String) = input.lines()
        .map { it.split(" ", "\t").map(String::toInt) }
        .sumOf { it.max() - it.min() }

    fun part2(input: String) = 0 // TODO

}