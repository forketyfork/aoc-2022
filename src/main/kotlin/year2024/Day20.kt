package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D

class Day20 {

    fun CharGrid.calculateDistances(start: Point2D) = buildMap {
        val queue = ArrayDeque<Pair<Point2D, Int>>()
        queue.add(start to 0)
        put(start, 0)
        while (queue.isNotEmpty()) {
            val (next, nextDistance) = queue.removeFirst()
            if (this@calculateDistances[next] != '#') {
                val startToNeighbour = nextDistance + 1
                Direction.SQUARE.forEach { dir ->
                    val neighbour = next.move(dir)
                    if (inBounds(neighbour) && !containsKey(neighbour)) {
                        queue.add(neighbour to startToNeighbour)
                        put(neighbour, startToNeighbour)
                    }
                }
            }
        }
    }

    fun part1(input: String, minSavedTime: Int): Int {

        val grid = CharGrid(input)
        val start = grid.findFirst('S')
        val end = grid.findFirst('E')

        val startCache = grid.calculateDistances(start)
        val endCache = grid.calculateDistances(end)

        val shortest = startCache[end]!!

        return grid.pointSequence().flatMap { cheatStart ->
            Direction.SQUARE.map { dir ->
                cheatStart to cheatStart.move(dir)
            }
        }
            .filter { (cheatStart, cheatEnd) -> grid.inBorder(cheatStart) && grid.inBorder(cheatEnd) && grid[cheatStart] == '#' && grid[cheatEnd] != '#' }
            .map { (cheatStart, cheatEnd) ->
                val path1Len = startCache[cheatStart]!!
                val path2Len = endCache[cheatEnd]!!
                shortest - (path1Len + 1 + path2Len)
            }.filter { it > 0 }
            .groupBy { it }
            .mapValues { (_, value) -> value.size }
            .filterKeys { it >= minSavedTime }
            .values.sum()
    }

    fun part2(input: String): Long {
        return 0L
    }

}
