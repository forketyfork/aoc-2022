package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D
import kotlin.math.min

class Day16 {

    fun part1(input: String) = solve(input).key

    fun part2(input: String) = solve(input).value.flatten().distinct().count().toLong()

    fun solve(input: String): Map.Entry<Long, MutableList<List<Point2D>>> {
        val grid = CharGrid(input)

        val dir = Direction.RIGHT
        val start = grid.findFirst('S')
        val end = grid.findFirst('E')

        return buildMap {
            grid.dfs(start, end, dir, this)
        }.iterator().next()
    }

    private fun CharGrid.dfs(
        start: Point2D,
        end: Point2D,
        dir: Direction,
        paths: MutableMap<Long, MutableList<List<Point2D>>>,
        path: List<Point2D> = listOf(start),
        minCost: MutableMap<Point2D, Pair<Direction, Long>> = mutableMapOf(),
        points: Long = 0
    ) {
        val startMinCost = minCost[start]
        val startMinCostAdjusted =
            if (startMinCost == null) Long.MAX_VALUE
            else startMinCost.second + dir.rotations(startMinCost.first) * 1000
        if (points > startMinCostAdjusted) {
            return
        }

        val newMinCost = min(startMinCostAdjusted, points)
        minCost[start] = dir to newMinCost
        if (start == end) {
            paths.computeIfAbsent(newMinCost) { mutableListOf() }.add(path)
            paths.keys.removeAll { it > newMinCost }
            return
        }

        listOf(dir, dir.rotate(Direction.RIGHT), dir.rotate(Direction.LEFT)).forEach { newDir ->
            val next = start.move(newDir)
            if (this[next] != '#') {
                val cost = points + (if (dir == newDir) 1 else 1001)
                dfs(next, end, newDir, paths, path + next, minCost, cost)
            }
        }

    }

}
