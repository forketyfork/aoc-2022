package year2025

import utils.Point2D
import kotlin.math.abs

class Day09 {

    fun part1(input: String): Long {
        val points = input.lines()
            .map {
                val (x, y) = it.split(",").map(String::toInt)
                Point2D(x, y)
            }
        return points.flatMap { point1 ->
            points.map { point2 ->
                abs(1L * (point2.x - point1.x + 1) * (point2.y - point1.y + 1))
            }
        }.max()
    }

    fun part2(input: String): Long {
        return 0L
    }

}
