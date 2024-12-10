package utils

class CharGrid(val input: String) {

    val grid = input.lines().map { it.toCharArray() }

    fun points(): List<Point2D> = grid.indices.flatMap { y ->
        grid[y].indices.map { x ->
            Point2D(x, y)
        }
    }

    fun at(pos: Point2D): Char {
        if (pos.y !in 0..grid.lastIndex || pos.x !in 0..grid[0].lastIndex) {
            return 0.toChar()
        }
        return grid[pos.x][pos.y]
    }

}