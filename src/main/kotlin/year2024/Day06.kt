package year2024

import utils.*

class Day06 {

    fun part1(input: String): Long {
        return pathLength(input).second
    }

    fun part2(input: String): Long {
        val lines = input.lines()
        return (0..<lines.size).sumOf { i ->
            (0..<lines[0].length).filter { j -> lines[i][j] == '.' }
                .count { j -> pathLength(input, i, j).first }.toLong()
        }
    }

    fun pathLength(input: String, obstacleRow: Int = -1, obstacleCol: Int = -1): Pair<Boolean, Long> {
        val array = input.lines().map { it.chars().toArray() }.toTypedArray()
        var row = array.indexOfFirst { it.contains('^'.code) }
        var col = array[row].indexOf('^'.code)
        if (obstacleRow >= 0 && obstacleCol >= 0) {
            array[obstacleRow][obstacleCol] = '#'.code
        }
        var path = 1L
        var dir = Direction.UP
        array[row][col] = dir.toString()[0].code
        while (row > 0 && row < array.size - 1 && col > 0 && col < array[0].size - 1) {
            val nextRow = row + dir.dy
            val nextCol = col + dir.dx
            val nextChar = array[nextRow][nextCol]
            val dirChar = dir.toString()[0].code
            if (nextChar == dirChar) {
                return true to path
            }
            if (array[nextRow][nextCol] == '#'.code) {
                dir = dir.rotate(Direction.RIGHT)
            } else {
                row = nextRow
                col = nextCol
                if (array[row][col] == '.'.code) {
                    array[row][col] = dirChar
                    path++
                }
            }
        }
        return false to path
    }

}
