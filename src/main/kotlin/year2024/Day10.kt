package year2024

import utils.*

class Day10 {

    fun part1(input: String) = solve(input) { it.last() }

    fun part2(input: String) = solve(input) { it }

    fun solve(input: String, extractor: (List<Point2D>) -> Any): Int {
        val grid = CharGrid(input)
        return grid.pointSequence().filter { grid[it] == '0' }.sumOf {
            buildList {
                grid.dfs(
                    start = it,
                    stopWhen = { grid[it] == '9' },
                    canMove = { prev, next -> get(next) - get(prev) == 1 },
                    found = this
                )
            }.map(extractor).distinct().size
        }
    }

}
