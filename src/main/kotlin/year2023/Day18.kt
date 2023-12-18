package year2023

import utils.Direction
import utils.Point2D

class Day18 {

    data class Move(val direction: Direction, val length: Int, val color: Int)

    fun part1(input: String): Long {

        val moves = parse(input)

        val positions = moves.runningFold(Point2D.ORIGIN) { point, move ->
            point.move(move.direction.dx * move.length, move.direction.dy * move.length)
        }

        val xs = positions.map { it.x }
        val ys = positions.map { it.y }

        val lx = xs.min()
        val rx = xs.max()
        val ly = ys.min()
        val ry = ys.max()

        val width = rx - lx + 1
        val height = ry - ly + 1

        val ox = -lx
        val oy = -ly

        val array = Array(height) { Array(width) { '.' } }

        moves.fold(Point2D(ox, oy)) { startPoint, move ->
            (0..<move.length).fold(startPoint) { currentPoint, _ ->
                array[currentPoint.y][currentPoint.x] = '#'
                currentPoint.move(move.direction)
            }
        }

        array.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, cell ->
                if (cell == '.') {
                    if (!array.fill(colIdx, rowIdx, '.', 'o')) {
                        array.fill(colIdx, rowIdx, 'o', '#')
                    }
                }
            }
        }

        return array.map { it.count { it == '#' }.toLong() }.sum()
    }

    fun Array<Array<Char>>.fill(col: Int, row: Int, fromCh: Char, toCh: Char): Boolean {

        val stack = ArrayDeque<Point2D>()
        stack.add(Point2D(col, row))

        var wasOnTheBorder = false

        while (stack.isNotEmpty()) {
            val point = stack.removeLast()
            if (this[point.y][point.x] == fromCh) {
                wasOnTheBorder =
                    wasOnTheBorder || point.x == 0 || point.y == 0 || point.x == this[0].size - 1 || point.y == this.size - 1
                this[point.y][point.x] = toCh
                if (point.x > 0 && this[point.y][point.x - 1] == fromCh) {
                    stack.add(point.move(-1, 0))
                }
                if (point.x < this[0].size - 1 && this[point.y][point.x + 1] == fromCh) {
                    stack.add(point.move(1, 0))
                }
                if (point.y > 0 && this[point.y - 1][point.x] == fromCh) {
                    stack.add(point.move(0, -1))
                }
                if (point.y < this.size - 1 && this[point.y + 1][point.x] == fromCh) {
                    stack.add(point.move(0, 1))
                }
            }

        }
        return wasOnTheBorder
    }

    fun part2(input: String): Long {
        return 0L
    }

    fun parse(input: String): List<Move> = input.lines().map { line ->
        val (part1, part2, part3) = line.split(' ')
        val direction = Direction.byFirstLetter(part1[0])
        val length = part2.toInt()
        val color = part3.substring(2, 8).toInt(16)
        Move(direction, length, color)
    }

}
