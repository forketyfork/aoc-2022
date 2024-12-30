package year2024

import utils.CharGrid
import utils.Direction

class Day20 {

    fun part1(input: String, minSavedTime: Int) = solve(input, minSavedTime, 2)
    fun part2(input: String, minSavedTime: Int) = solve(input, minSavedTime, 20)

    fun solve(input: String, minSavedTime: Int, distance: Int): Int {

        val grid = CharGrid(input)
        val start = grid.findFirst('S')
        val end = grid.findFirst('E')

        val path = buildList {
            add(start)
            var curr = start
            var lastDir: Direction = Direction.SQUARE.find { grid[curr.move(it)] != '#' }!!
            while (curr != end) {
                lastDir = listOf(
                    lastDir,
                    lastDir.rotate(Direction.RIGHT),
                    lastDir.rotate(Direction.LEFT)
                ).find { grid[curr.move(it)] != '#' }!!
                curr = curr.move(lastDir)
                add(curr)
            }
        }

        return buildList {
            (0..path.lastIndex - distance).forEach { idx1 ->
                (idx1 + distance..path.lastIndex).forEach { idx2 ->
                    val manhattanDistance = path[idx1].manhattan(path[idx2])
                    if (manhattanDistance < (idx2 - idx1) && manhattanDistance <= distance) {
                        add(idx2 - idx1 - manhattanDistance)
                    }
                }
            }
        }.count { it >= minSavedTime }
    }

}
