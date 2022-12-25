package year2022

fun String.fromSnafu() = fold(0L) { acc, char -> acc * 5 + "=-012".indexOf(char) - 2 }

fun Long.toSnafu() = generateSequence(this to "") { (number, acc) ->
    val remainder = number.mod(5)
    (number + 5 * (remainder / 3)) / 5 to "012=-"[remainder] + acc
}.first { it.first == 0L }.second

class Day25(val contents: String) {

    fun part1() = contents.lines().map(String::fromSnafu).sum().toSnafu()

}
