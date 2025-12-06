package year2025

import utils.CharGrid
import utils.Direction
import utils.Point2D

class Day04 {

    fun part1(input: String) = CharGrid(input).accessible().count()

    fun part2(input: String): Int {
        val grid = CharGrid(input)
        return generateSequence {
            with(grid.accessible().toList()) {
                if (isEmpty()) {
                    null
                } else {
                    forEach { grid[it] = '.' }
                    size
                }
            }
        }.sum()
    }

    fun CharGrid.accessible(): Sequence<Point2D> = pointSequence().filter { point ->
        this[point] == '@' && Direction.entries.count { dir ->
            this[point.move(dir)] == '@'
        } < 4
    }

}
