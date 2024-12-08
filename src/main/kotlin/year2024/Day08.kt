package year2024

import utils.*

class Day08 {

    fun part1(input: String): Int {
        val lines = input.lines()
        return lines.withIndex()
            .flatMap { (y, line) ->
                line.withIndex()
                    .map { (x, char) -> char to Point2D(x, y) }
            }
            .groupBy({ it.first }, { it.second })
            .filter { it.key != '.' }
            .values.flatMap { points ->
                points.flatMap { point1 ->
                    (points - point1).map { point2 ->
                        point2.move(point2.x - point1.x, point2.y - point1.y)
                    }
                }
            }.filter { it.x in 0..<lines[0].length && it.y in 0..<lines.size }
            .distinct()
            .size
    }

    fun part2(input: String): Int {
        val lines = input.lines()
        return lines.withIndex()
            .flatMap { (y, line) ->
                line.withIndex()
                    .map { (x, char) -> char to Point2D(x, y) }
            }
            .groupBy({ it.first }, { it.second })
            .filter { it.key != '.' }
            .values.flatMap { points ->
                points.flatMap { point1 ->
                    (points - point1).flatMap { point2 ->
                        buildSet {
                            val dx = point2.x - point1.x
                            val dy = point2.y - point1.y
                            var point = point2
                            while (point.x in 0..<lines[0].length && point.y in 0..<lines.size) {
                                add(point)
                                point = point.move(dx, dy)
                            }
                        }
                    }
                }
            }
            .distinct()
            .size
    }

}
