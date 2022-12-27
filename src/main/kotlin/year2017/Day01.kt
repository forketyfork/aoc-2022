package year2017

class Day01 {

    fun part1(input: String) = input.asSequence()
        .plus(input.first())
        .map { it - '0' }
        .windowed(2)
        .filter { (first, second) -> first == second }
        .sumOf { it[0] }

    fun part2(input: String) = 2 * (0..<input.length / 2)
        .filter { input[it] == input[it + input.length / 2] }
        .sumOf { input[it] - '0' }

}