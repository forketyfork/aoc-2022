package year2023

class Day01 {

    companion object {
        val digits = (0..9).associateBy { it.toString() }
        private val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
            .withIndex().associate { (idx, word) -> word to idx + 1 }
        val digitsAndWords = digits + words
    }

    fun part1(input: String): Int = solveForDigitMap(input, digits)

    fun part2(input: String): Int = solveForDigitMap(input, digitsAndWords)

    private fun solveForDigitMap(input: String, digitMap: Map<String, Int>): Int = input.lines()
        .filter { it.isNotBlank() }
        .sumOf { line ->
            val (_, firstDigit) = line.findAnyOf(digitMap.keys)!!
            val (_, lastDigit) = line.findLastAnyOf(digitMap.keys)!!
            "${digitMap[firstDigit]}${digitMap[lastDigit]}".toInt()
        }

}
