package year2024

import utils.CharGrid
import utils.Point2D

class Day18 {

    fun part1(input: String): Int {
        val (size, simulations, blocks) = parse(input)
        val grid = CharGrid((".".repeat(size) + "\n").repeat(size))
        blocks.take(simulations).forEach { grid[it] = '#' }
        val end = Point2D(size - 1, size - 1)
        return grid.bfs(
            start = Point2D.ORIGIN,
            stopWhen = { it == end },
            canMove = { _, p2 -> grid[p2] != '#' },
        ).size - 1
    }

    fun part2(input: String): Point2D {
        val (size, _, blocks) = parse(input)
        val grid = CharGrid((".".repeat(size) + "\n").repeat(size))
        blocks.forEach { block ->
            grid[block] = '#'
            val end = Point2D(size - 1, size - 1)
            grid.resetMarks()
            if (grid.bfs(
                    start = Point2D.ORIGIN,
                    stopWhen = { it == end },
                    canMove = { _, p2 -> grid[p2] != '#' },
                ).isEmpty()
            ) {
                return block
            }
        }
        return Point2D(-1, -1)
    }

    private fun parse(input: String): Triple<Int, Int, List<Point2D>> {
        val lines = input.lines()
        val size = lines.first().toInt()
        val simulations = lines[1].toInt()
        val blocks = lines.drop(2).map { it.split(',').map { it.toInt() } }.map { Point2D(it[0], it[1]) }
        return Triple(size, simulations, blocks)
    }
}
