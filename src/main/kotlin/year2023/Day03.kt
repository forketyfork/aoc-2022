package year2023

import utils.Direction

typealias Coords = Pair<Int, Int>

class Day03 {

    fun List<List<Char>>.findAllAdjacentNumbers(rowIdx: Int, colIdx: Int): Set<Coords> {
        val matrix = this
        return buildSet {
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

    fun List<List<Char>>.readNumber(coords: Coords): Int {
        var number = 0
        var (row, col) = coords
        while (col in this[row].indices && this[row][col].isDigit()) {
            number = number * 10 + (this[row][col] - '0')
            col += 1
        }
        return number
    }

    fun buildMatrix(input: String): List<List<Char>> =
        input.lines()
            .filter { it.isNotBlank() }
            .map(CharSequence::toList)


    fun part1(input: String): Int {

        val matrix = buildMatrix(input)

        return buildSet {
            matrix.forEachIndexed { rowIdx, rowString ->
                rowString.forEachIndexed { colIdx, cell ->
                    if (!cell.isDigit() && cell != '.') {
                        addAll(matrix.findAllAdjacentNumbers(rowIdx, colIdx))
                    }
                }
            }
        }.sumOf { matrix.readNumber(it) }
    }

    fun part2(input: String): Int {

        val matrix = buildMatrix(input)

        return matrix.flatMapIndexed { rowIdx, rowString ->
            rowString.mapIndexed { colIdx, cell ->
                if (cell == '*') {
                    val numbers = matrix.findAllAdjacentNumbers(rowIdx, colIdx).toList()
                    if (numbers.size == 2) {
                        val number1 = matrix.readNumber(numbers[0])
                        val number2 = matrix.readNumber(numbers[1])
                        return@mapIndexed number1 * number2
                    }
                }
                0
            }
        }.sum()

    }

}