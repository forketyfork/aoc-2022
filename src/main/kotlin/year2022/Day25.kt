package year2022

fun String.fromSnafu() = fold(0L) { acc, char -> acc * 5 + "=-012".indexOf(char) - 2 }

fun Long.toSnafu(): String {
    var number = this
    return buildString {
        while (number > 0L) {
            val remainder = number % 5
            number += if (remainder > 2) 5 else 0
            number /= 5
            append(
                when (remainder) {
                    0L -> '0'
                    1L -> '1'
                    2L -> '2'
                    3L -> '='
                    else -> '-'
                }
            )
        }
    }.reversed()
}


class Day25(val contents: String) {

    fun part1() = contents.lines().map(String::fromSnafu).sum().toSnafu()

}
