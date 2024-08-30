package year2023

import utils.Direction

class Day10 {

    private val stepMap = buildMap {
        put(Direction.DOWN to '|', Direction.DOWN)
        put(Direction.UP to '|', Direction.UP)
        put(Direction.LEFT to '-', Direction.LEFT)
        put(Direction.RIGHT to '-', Direction.RIGHT)
        put(Direction.DOWN to 'L', Direction.RIGHT)
        put(Direction.LEFT to 'L', Direction.UP)
        put(Direction.DOWN to 'J', Direction.LEFT)
        put(Direction.RIGHT to 'J', Direction.UP)
        put(Direction.RIGHT to '7', Direction.DOWN)
        put(Direction.UP to '7', Direction.LEFT)
        put(Direction.UP to 'F', Direction.RIGHT)
        put(Direction.LEFT to 'F', Direction.DOWN)
    }

    fun part1(input: String): Int {
        val cells = input.lines().map { it.toCharArray() }.toTypedArray()

        val sRow = cells.indexOfFirst { it.contains('S') }
        val sCol = cells[sRow].indexOf('S')

        val sDirection = if (sRow > 0 && cells[sRow - 1][sCol] in setOf('|', '7', 'F')) {
            Direction.UP
        } else if (sCol > 0 && cells[sRow][sCol - 1] in setOf('-', 'F', 'L')) {
            Direction.LEFT
        } else if (sCol < cells[sRow].size - 1 && cells[sRow][sCol + 1] in setOf('-', 'J', '7')) {
            Direction.RIGHT
        } else {
            Direction.DOWN
        }

        return generateSequence(Triple(sDirection, sRow, sCol)) { (direction, row, col) ->
            val nextRow = row + direction.dy
            val nextCol = col + direction.dx
            if (cells[nextRow][nextCol] == 'S') {
                null
            } else {
                Triple(stepMap[direction to cells[nextRow][nextCol]]!!, nextRow, nextCol)
            }
        }.count() / 2
    }

    fun part2(input: String): Int {
        return 0
    }

}
