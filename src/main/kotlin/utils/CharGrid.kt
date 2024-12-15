package utils

import java.util.*

class CharGrid(val input: String) {

    private val grid = input.lines().map { it.toCharArray() }
    private val marks = input.lines().map { BitSet(it.length) }

    fun points(): Sequence<Point2D> = generateSequence(Point2D(0, 0)) {
        if (it.x == grid[it.y].lastIndex) {
            if (it.y == grid.lastIndex) {
                null
            } else {
                Point2D(0, it.y + 1)
            }
        } else {
            Point2D(it.x + 1, it.y)
        }
    }

    operator fun get(pos: Point2D): Char {
        if (pos.y !in 0..grid.lastIndex || pos.x !in 0..grid[0].lastIndex) {
            return 0.toChar()
        }
        return grid[pos.y][pos.x]
    }

    operator fun set(pos: Point2D, ch: Char) {
        grid[pos.y][pos.x] = ch
    }

    fun mark(pos: Point2D) {
        marks[pos.y].set(pos.x)
    }

    fun isMarked(pos: Point2D): Boolean {
        return marks[pos.y][pos.x]
    }

    fun isClear(pos: Point2D): Boolean {
        return !marks[pos.y][pos.x]
    }

    fun searchPaths(
        start: Point2D,
        terminator: Char,
        directions: List<Direction> = Direction.SQUARE,
        canMove: CharGrid.(Point2D, Point2D) -> Boolean,
        found: MutableList<List<Point2D>>,
        path: List<Point2D> = listOf(start)
    ) {
        if (this[start] == terminator) {
            found.add(path)
            return
        }
        for (direction in directions) {
            val next = start.move(direction)
            if (next !in path && canMove(start, next)) {
                searchPaths(next, terminator, directions, canMove, found, path + next)
            }
        }
    }

    override fun toString(): String = grid.joinToString("\n") { String(it) }

}
