package year2015

import utils.isEven
import java.security.MessageDigest

class Day04 {

    private val md = MessageDigest.getInstance("MD5")

    fun part1(input: String) = solution(input, 5)

    fun part2(input: String) = solution(input, 6)

    private fun solution(input: String, zeros: Int): Int {
        return generateSequence(0) { it + 1 }.first { value ->
            val digest = md.digest((input + value).toByteArray())
            digest.take(zeros / 2).all { it == 0.toByte() }
                    && (zeros.isEven()
                    || digest[zeros / 2] in (0..15))
        }

    }
}