package year2022

import utils.Direction
import utils.Point2D
import kotlin.math.abs
import kotlin.math.sign

class Day09 {

    private fun solution(input: String, len: Int): Int {
        val positions = mutableSetOf<Point2D>()
        val parts = MutableList(len) { Point2D(0, 0) }
        positions.add(parts.last())
        input.lines().forEach { line ->
            val steps = line.substringAfter(" ").toInt()
            val (dx, dy) = Direction.byFirstLetter(line.substringBefore(" ")[0])
            repeat(steps) {
                parts[0] = parts[0].move(dx, dy)
                parts.indices.windowed(2, partialWindows = false).forEach { (i1, i2) ->
                    val part1 = parts[i1]
                    val part2 = parts[i2]
                    if (abs(part1.y - part2.y) >= 2 || abs(part1.x - part2.x) >= 2) {
                        parts[i2] = if (part1.x != part2.x && part1.y != part2.y) {
                            part2.move((part1.x - part2.x).sign, (part1.y - part2.y).sign)
                        } else if (abs(part1.x - part2.x) == 2) part2.move((part1.x - part2.x).sign, 0)
                        else part2.move(0, (part1.y - part2.y).sign)
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
