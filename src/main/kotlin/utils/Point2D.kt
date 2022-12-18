package utils

import kotlin.math.abs

data class Point2D(val x: Int, val y: Int) {

    fun manhattan(other: Point2D): Int {
        return abs(other.x - this.x) + abs(other.y - this.y)
    }

    fun move(dir: Direction) = dir.let { (dx, dy) -> move(dx, dy) }

    fun move(dx: Int = 0, dy: Int = 0) = copy(x = x + dx, y = y + dy)

    companion object {
        val ORIGIN = Point2D(0, 0)
        fun parse(string: String): Point2D {
            val (x, y) = string.split(",").map(String::toInt)
            return Point2D(x, y)
        }
    }
}
