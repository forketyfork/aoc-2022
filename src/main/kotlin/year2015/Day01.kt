package year2015

class Day01 {

    fun part1(input: String) = runningFold(input).last()
    fun part2(input: String) = runningFold(input).indexOfFirst { it < 0 }

    private fun runningFold(input: String) = input.runningFold(0) { acc, char ->
        acc + if (char == '(') 1 else -1
    }

}