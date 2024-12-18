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
        if (!inBounds(pos)) {
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

    fun isClear(pos: Point2D) = !isMarked(pos)

    fun inBounds(pos: Point2D) = pos.y in 0..grid.lastIndex && pos.x in 0..grid[pos.y].lastIndex

    fun dfs(
        start: Point2D,
        stopWhen: (Point2D) -> Boolean,
        directions: List<Direction> = Direction.SQUARE,
        canMove: CharGrid.(Point2D, Point2D) -> Boolean,
        found: MutableList<List<Point2D>>,
        path: List<Point2D> = listOf(start)
    ) {
        if (stopWhen(start)) {
            found.add(path)
            return
        }
        for (direction in directions) {
            val next = start.move(direction)
            if (inBounds(next) && next !in path && canMove(start, next)) {
                dfs(next, stopWhen, directions, canMove, found, path + next)
            }
        }
    }

    fun bfs(
        start: Point2D,
        stopWhen: (Point2D) -> Boolean,
        directions: List<Direction> = Direction.SQUARE,
        canMove: CharGrid.(Point2D, Point2D) -> Boolean,
    ): List<Point2D> {
        val queue = ArrayDeque<Point2D>()
        val previous = mutableMapOf<Point2D, Point2D>()
        mark(start)
        queue.add(start)
        while (queue.isNotEmpty()) {
            val element = queue.removeFirst()
            for (direction in directions) {
                val next = element.move(direction)
                if (inBounds(next) && isClear(next) && canMove(element, next)) {
                    previous[next] = element
                    if (stopWhen(next)) {
                        var step: Point2D? = element
                        return buildList {
                            while (step != null) {
                                add(step)
                                step = previous[step]
                            }
                        }.reversed()
                    } else {
                        mark(next)
                        queue.add(next)
                    }
                }
            }
        }
        return emptyList<Point2D>()
    }

    override fun toString(): String = grid.joinToString("\n") { String(it) }

}
