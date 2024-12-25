package year2024

import utils.CharGrid
import utils.Direction

class Day15 {

    fun part1(input: String): Long {
        val (field, movements) = input.split("\n\n")
        val grid = CharGrid(field)
        val dirs = movements.lines().joinToString("").map { Direction.byArrow(it) }
        var robot = grid.findFirst('@')
        dirs.forEach { dir ->
            val next = robot.move(dir)
            when (grid[next]) {
                '.' -> {
                    grid[robot] = '.'
                    robot = next
                    grid[robot] = '@'
                }

                'O' -> {
                    var p = next
                    while (grid[p] == 'O') {
                        p = p.move(dir)
                    }
                    if (grid[p] == '.') {
                        grid[p] = 'O'
                        grid[robot] = '.'
                        robot = next
                        grid[robot] = '@'
                    }
                }
            }
        }
        return grid.pointSequence().filter { grid[it] == 'O' }.sumOf { it.y * 100L + it.x }
    }

    fun part2(input: String): Long {
        return 0L
    }

}
