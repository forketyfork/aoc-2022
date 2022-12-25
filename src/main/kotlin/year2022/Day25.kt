package year2022

fun String.fromSnafu(): Long {
    var number = 0L
    var power = 1L
    for (i in (length - 1 downTo 0)) {
        val digit = when (this[i]) {
            '0' -> 0
            '1' -> 1
            '2' -> 2
            '-' -> -1
            '=' -> -2
            else -> error(this)
        }
        number += (power * digit)
        power *= 5
    }
    return number
}

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


    fun part1() = contents.lines().map(String::fromSnafu).sum().also { println(it) }.toSnafu()


}
