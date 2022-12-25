package year2022

import kotlin.math.max

class Day25(val contents: String) {

    private val digits = "=-012"

    fun part1() = contents.lines().fold("0") { acc, line ->
        val maxLen = max(line.length, acc.length) + 1
        acc.padStart(maxLen, '0').zip(line.padStart(maxLen, '0'))
            .foldRight("" to 0) { (a, b), (result, carry) ->
                val sum = digits.indexOf(a) + digits.indexOf(b) - 4 + carry
                (digits[(sum + 7) % 5] + result) to sum / 3
            }.first.trimStart('0')
    }

}
