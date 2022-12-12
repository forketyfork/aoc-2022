package year2022

import kotlin.math.abs
import kotlin.math.sign

class Day09 {

    data class Position(val x: Int, val y: Int)

    private fun solution(input: String, len: Int): Int {
        val positions = mutableSetOf<Position>()
        val parts = MutableList(len) { Position(0, 0) }
        positions.add(parts.last())
        input.lines().forEach { line ->
            val direction = line.substringBefore(" ")
            val steps = line.substringAfter(" ").toInt()
            val (dx, dy) = when (direction) {
                "R" -> Pair(1, 0)
                "L" -> Pair(-1, 0)
                "D" -> Pair(0, 1)
                "U" -> Pair(0, -1)
                else -> error("Can't parse input: $line")
            }
            repeat(steps) {
                parts[0] = parts[0].copy(x = parts[0].x + dx, y = parts[0].y + dy)
                parts.indices.windowed(2, partialWindows = false).forEach { (i1, i2) ->
                    val part1 = parts[i1]
                    val part2 = parts[i2]
                    if (abs(part1.y - part2.y) >= 2 || abs(part1.x - part2.x) >= 2) {
                        parts[i2] = if (part1.x != part2.x && part1.y != part2.y) {
                            part2.copy(
                                x = part2.x + (part1.x - part2.x).sign,
                                y = part2.y + (part1.y - part2.y).sign
                            )
                        } else if (abs(part1.x - part2.x) == 2) part2.copy(x = part2.x + (part1.x - part2.x).sign)
                        else part2.copy(y = part2.y + (part1.y - part2.y).sign)
                    }
                }
                positions.add(parts.last())
            }
        }
        return positions.size

    }

    fun part2(input: String) = solution(input, 10)

    fun part1(input: String) = solution(input, 2)
}
