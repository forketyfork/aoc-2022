package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D
import kotlin.math.min

class Day16 {

    fun part1(input: String): Long {
        val grid = CharGrid(input)

        val dir = Direction.RIGHT
        val start = grid.findFirst('S')
        val end = grid.findFirst('E')

        val result = mutableMapOf<Point2D, Long>()
        grid.dfs(start, end, dir, result)
        return result[end]!!
    }

    private fun CharGrid.dfs(
        start: Point2D,
        end: Point2D,
        dir: Direction,
        minCost: MutableMap<Point2D, Long>,
        points: Long = 0
    ) {
        val startMinCost = minCost[start] ?: Long.MAX_VALUE
        if (points > startMinCost) {
            return
        }
        minCost[start] = min(startMinCost, points)
        if (start == end) {
            return
        }

        listOf(dir, dir.rotate(Direction.RIGHT), dir.rotate(Direction.LEFT)).forEach { newDir ->
            val next = start.move(newDir)
            if (this[next] != '#') {
                val cost = points + (if (dir == newDir) 1 else 1001)
                dfs(next, end, newDir, minCost, cost)
            }
        }

    }

    fun part2(input: String): Long {
        return 0L
    }

}
