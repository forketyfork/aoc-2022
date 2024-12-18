package year2024

import utils.CharGrid
import utils.Point2D

class Day18 {

    fun part1(input: String): Long {
        val (size, simulations, blocks) = parse(input)
        val grid = CharGrid((".".repeat(size) + "\n").repeat(size))
        blocks.take(simulations).forEach { grid[it] = '#' }
        val end = Point2D(size - 1, size - 1)
        return grid.bfs(
            start = Point2D(0, 0),
            stopWhen = { it == end },
            canMove = { p1, p2 -> grid[p2] != '#' },
        ).size.toLong()
    }

    fun part2(input: String): Long {
        return 0L
    }

    private fun parse(input: String): Triple<Int, Int, List<Point2D>> {
        val lines = input.lines()
        val size = lines.first().toInt()
        val simulations = lines[1].toInt()
        val blocks = lines.drop(2).map { it.split(',').map { it.toInt() } }.map { Point2D(it[0], it[1]) }
        return Triple(size, simulations, blocks)
    }
}
