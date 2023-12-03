package year2023

import utils.Direction


class Day03 {

    fun part1(input: String): Int {
        val matrix = input.lines()
            .filter { it.isNotBlank() }
            .map(CharSequence::toList)

        return buildSet {
            matrix.forEachIndexed { rowIdx, rowString ->
                rowString.forEachIndexed { colIdx, cell ->
                    if (!cell.isDigit() && cell != '.') {
                        Direction.entries.forEach { (dx, dy) ->
                            val row = rowIdx + dy
                            var col = colIdx + dx
                            if (row in matrix.indices && col in matrix[row].indices && matrix[row][col].isDigit()) {
                                while (col > 0 && matrix[row][col - 1].isDigit()) {
                                    col -= 1
                                }
                                add(row to col)
                            }
                        }
                    }
                }
            }
        }.map { pair ->
            var number = 0
            var (row, col) = pair
            while (col in matrix[row].indices && matrix[row][col].isDigit()) {
                number = number * 10 + (matrix[row][col] - '0')
                col += 1
            }
            number
        }.sum()
    }

    fun part2(input: String): Int = TODO()

}