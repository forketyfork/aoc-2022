package year2024

import utils.*

class Day10 {

    fun part1(input: String) = solve(input) { it.last() }

    fun part2(input: String) = solve(input) { it }

    fun solve(input: String, extractor: (List<Point2D>) -> Any): Int {
        val grid = input.lines().map { it.toCharArray() }
        return grid.indices.sumOf { y ->
            grid[y].indices.sumOf { x ->
                val point = Point2D(x, y)
                if (grid.at(point) == '0') {
                    buildList { grid.search(point, this) }.map(extractor).distinct().size
                } else {
                    0
                }
            }
        }
    }

    fun List<CharArray>.search(start: Point2D, found: MutableList<List<Point2D>>, path: List<Point2D> = listOf(start)) {
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

    fun List<CharArray>.at(pos: Point2D): Char {
        if (pos.y !in 0..lastIndex || pos.x !in 0..get(0).lastIndex) {
            return 0.toChar()
        }
        return get(pos.y)[pos.x]
    }

}
