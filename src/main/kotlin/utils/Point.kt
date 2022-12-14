package utils

data class Point(val x: Int, val y: Int) {

    fun move(dir: Direction) = copy(x = x + dir.component1(), y = y + dir.component2())

    companion object {
        val ORIGIN = Point(0, 0)
        fun parse(string: String): Point {
            val (x, y) = string.split(",").map(String::toInt)
            return Point(x, y)
        }
    }
}
