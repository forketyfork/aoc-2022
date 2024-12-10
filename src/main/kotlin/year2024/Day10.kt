package year2024

import utils.*

class Day10 {

    fun part1(input: String) = solve(input) { it.last() }

    fun part2(input: String) = solve(input) { it }

    fun solve(input: String, extractor: (List<Point2D>) -> Any): Int {
        val grid = CharGrid(input)
        return grid.points().filter { grid.at(it) == '0' }.sumOf {
            buildList { grid.search(it, this) }.map(extractor).distinct().size
        }
    }

    fun CharGrid.search(start: Point2D, found: MutableList<List<Point2D>>, path: List<Point2D> = listOf(start)) {
        if (at(start) == '9') {
            found.add(path)
            return
        }
        for (direction in Direction.SQUARE) {
            val next = start.move(direction)
            if (next !in path && at(next) - at(start) == 1) {
                search(next, found, path + next)
            }
        }
    }

}
