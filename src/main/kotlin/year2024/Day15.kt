package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D

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
        val (field, movements) = input.split("\n\n")

        val stretchedField = field.lines().joinToString("\n") { line ->
            line.map { char ->
                when (char) {
                    '#' -> "##"
                    'O' -> "[]"
                    '.' -> ".."
                    '@' -> "@."
                    else -> error("Unknown cell type: $char")
                }
            }.joinToString("")
        }

        val grid = CharGrid(stretchedField)
        val dirs = movements.lines().joinToString("").map { Direction.byArrow(it) }
        var robot = grid.findFirst('@')
        dirs.forEach { dir ->
            if (grid.shift(robot, dir, '@', true)) {
                grid.shift(robot, dir, '@', false)
                grid[robot] = '.'
                robot = robot.move(dir)
            }
        }
        return grid.pointSequence().filter { grid[it] == '[' }.sumOf { it.y * 100L + it.x }
    }

    fun CharGrid.shift(cell: Point2D, dir: Direction, replaceWith: Char, dryRun: Boolean): Boolean {
        val neighbour = cell.move(dir)

        return when (get(neighbour)) {
            '.' -> {
                if (!dryRun) {
                    set(neighbour, replaceWith)
                }
                true
            }

            '#' -> false
            '[' -> {
                when (dir) {
                    Direction.LEFT, Direction.RIGHT -> {
                        if (shift(neighbour, dir, '[', dryRun)) {
                            if (!dryRun) {
                                set(neighbour, replaceWith)
                            }
                            return true
                        } else {
                            return false
                        }
                    }

                    Direction.DOWN, Direction.UP -> {
                        if (shift(neighbour, dir, '[', dryRun) && shift(
                                neighbour.move(Direction.RIGHT),
                                dir,
                                ']',
                                dryRun
                            )
                        ) {
                            if (!dryRun) {
                                set(neighbour, replaceWith)
                                set(neighbour.move(Direction.RIGHT), '.')
                            }
                            return true
                        } else {
                            return false
                        }
                    }

                    else -> error("Impossible")
                }
            }

            ']' -> {
                return when (dir) {
                    Direction.LEFT, Direction.RIGHT -> {
                        if (shift(neighbour, dir, ']', dryRun)) {
                            if (!dryRun) {
                                set(neighbour, replaceWith)
                            }
                            true
                        } else {
                            false
                        }
                    }

                    Direction.DOWN, Direction.UP -> {
                        if (shift(neighbour, dir, ']', dryRun) && shift(
                                neighbour.move(Direction.LEFT),
                                dir,
                                '[',
                                dryRun
                            )
                        ) {
                            if (!dryRun) {
                                set(neighbour, replaceWith)
                                set(neighbour.move(Direction.LEFT), '.')
                            }
                            true
                        } else {
                            false
                        }
                    }

                    else -> error("Impossible")
                }

            }

            else -> error("Neighbour: ${get(neighbour)}")
        }
    }

}
