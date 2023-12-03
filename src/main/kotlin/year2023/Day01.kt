package year2023

class Day01 {


    fun part1(input: String): Int = input.lines()
        .filter { it.isNotBlank() }
        .map { line ->
            ((line.firstOrNull { it.isDigit() } ?: '0') - '0') * 10 +
                    ((line.lastOrNull { it.isDigit() } ?: '0') - '0')
        }.sum()

    fun part2(input: String): Int = input.lines()
        .filter { it.isNotBlank() }
        .map { line ->
            findFirst(line) * 10 + findLast(line)
        }.sum()


    val digitsAsStrings = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun findFirst(line: String): Int {
        return if (line.first().isDigit()) {
            line.first() - '0'
        } else {
            digitsAsStrings.firstNotNullOfOrNull {
                if (line.startsWith(it.key)) it.value else null
            } ?: findFirst(line.substring(1))
        }
    }

    fun findLast(line: String): Int {
        return if (line.last().isDigit()) {
            line.last() - '0'
        } else {
            digitsAsStrings.firstNotNullOfOrNull {
                if (line.endsWith(it.key)) it.value else null
            } ?: findLast(line.substring(0, line.length - 1))
        }
    }

}
