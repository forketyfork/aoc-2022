package year2024

import utils.CharGrid
import utils.Direction

class Day20 {

    fun part1(input: String, minSavedTime: Int): Int {

        val grid = CharGrid(input)
        val start = grid.findFirst('S')
        val end = grid.findFirst('E')
        val shortest = grid.bfs(start, stopWhen = { it == end }, canMove = { _, to -> grid[to] != '#' }).size

        return grid.pointSequence().flatMap { cheatStart ->
            Direction.SQUARE.map { dir ->
                cheatStart to cheatStart.move(dir)
            }
        }
            .filter { (cheatStart, cheatEnd) -> grid[cheatStart] == '#' && grid[cheatEnd] != '#' }
            .map { (cheatStart, cheatEnd) ->
                grid.resetMarks()
                grid[cheatStart] = '.'
                val path = grid.bfs(start, stopWhen = { it == end }, canMove = { _, to -> grid[to] != '#' })
                grid[cheatStart] = '#'
                val cheatStartIdx = path.indexOf(cheatStart)
                if (cheatStartIdx != -1 && cheatStartIdx < path.lastIndex && path[cheatStartIdx + 1] == cheatEnd) {
                    shortest - path.size
                } else {
                    0
                }
            }.filter { it != 0 }
            .groupBy { it }
            .mapValues { (_, value) -> value.size }
            .filterKeys { it >= minSavedTime }.values.sum()
    }

    fun part2(input: String): Long {
        return 0L
    }

}
