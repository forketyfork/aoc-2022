import kotlin.math.abs
import kotlin.math.sign

fun main() {

    data class Position(val x: Int, val y: Int)

    fun solution(input: List<String>, len: Int): Int {
        val positions = mutableSetOf<Position>()
        val parts = MutableList(len) { Position(0, 0) }
        positions.add(parts.last())
        input.forEach { line ->
            val direction = line.substringBefore(" ")
            val steps = line.substringAfter(" ").toInt()
            val (dx, dy) = when (direction) {
                "R" -> Pair(1, 0)
                "L" -> Pair(-1, 0)
                "D" -> Pair(0, 1)
                "U" -> Pair(0, -1)
                else -> throw RuntimeException("Can't parse input: $line")
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
                        } else if (part1.x - part2.x == 2) part2.copy(x = part2.x + 1)
                        else if (part1.x - part2.x == -2) part2.copy(x = part2.x - 1)
                        else if (part1.y - part2.y == 2) part2.copy(y = part2.y + 1)
                        else part2.copy(y = part2.y - 1)
                    }
                }
                positions.add(parts.last())
            }
        }
        return positions.size

    }

    fun part2(input: List<String>) = solution(input, 10)

    fun part1(input: List<String>) = solution(input, 2)

    val input = readLines("Day09_test")

    part1(input).also { println(it) }.also { check(it == 6406) }
    part2(input).also { println(it) }.also { check(it == 2643) }
}
