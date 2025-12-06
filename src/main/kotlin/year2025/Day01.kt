package year2025

import kotlin.math.abs

class Day01 {

    companion object {
        const val START = 50
        const val SIZE = 100
    }

    fun part1(input: String): Int = parse(input)
        .runningFold(START) { acc, v -> getNewPos(acc, v) }
        .count { it == 0 }

    fun part2(input: String): Int = parse(input)
        .fold(arrayOf(START, 0)) { acc, v ->
            val newPos = acc[0] + v
            acc[1] += abs(newPos / SIZE)
            if (acc[0] > 0 && newPos <= 0) {
                acc[1]++
            }
            acc[0] = getNewPos(acc[0], v)
            acc
        }[1]

    private fun parse(input: String) =
        input.lines().map {
            it.replace('L', '-')
                .replace('R', '+')
                .toInt()
        }

    private fun getNewPos(oldPos: Int, value: Int): Int {
        val newPos = (oldPos + value) % SIZE
        return if (newPos < 0) {
            newPos + SIZE
        } else {
            newPos
        }
    }

}
