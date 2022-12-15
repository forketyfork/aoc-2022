package utils

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {

    fun manhattan(other: Point): Int {
        return abs(other.x - this.x) + abs(other.y - this.y)
    }

    fun move(dir: Direction) = dir.let { (dx, dy) -> move(dx, dy) }

    fun move(dx: Int = 0, dy: Int = 0) = copy(x = x + dx, y = y + dy)

    companion object {
        val ORIGIN = Point(0, 0)
        fun parse(string: String): Point {
            val (x, y) = string.split(",").map(String::toInt)
            return Point(x, y)
        }
    }
}
