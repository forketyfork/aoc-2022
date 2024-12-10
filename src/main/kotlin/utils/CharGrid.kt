package utils

class CharGrid(val input: String) {

    val grid = input.lines().map { it.toCharArray() }

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

    fun at(pos: Point2D): Char {
        if (pos.y !in 0..grid.lastIndex || pos.x !in 0..grid[0].lastIndex) {
            return 0.toChar()
        }
        return grid[pos.x][pos.y]
    }

}