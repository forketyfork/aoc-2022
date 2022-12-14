package utils

data class Point(val x: Int, val y: Int) {

    fun move(dir: Direction) = dir.let { (dx, dy) -> move(dx, dy) }

    fun move(dx: Int, dy: Int) = copy(x = x + dx, y = y + dy)

    companion object {
        val ORIGIN = Point(0, 0)
        fun parse(string: String): Point {
            val (x, y) = string.split(",").map(String::toInt)
            return Point(x, y)
        }
    }
}
