package utils

enum class Direction(val dx: Int, val dy: Int) {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT_UP(-1, -1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1, 1),
    LEFT_DOWN(-1, 1);

    override fun toString() = when (this) {
        LEFT -> "<"
        RIGHT -> ">"
        UP -> "^"
        DOWN -> "v"
        else -> "*" // TODO
    }

    fun rotate(direction: Direction): Direction = when (direction) {
        RIGHT -> when (this) {
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP -> RIGHT
            else -> error("Can't rotate $this to $direction")
        }

        LEFT -> when (this) {
            RIGHT -> UP
            UP -> LEFT
            LEFT -> DOWN
            DOWN -> RIGHT
            else -> error("Can't rotate $this to $direction")
        }

        else -> error("Can't rotate $this to $direction")
    }

    fun rotations(direction: Direction): Int {
        return when (direction) {
            this -> 0
            this.rotate(RIGHT), this.rotate(LEFT) -> 1
            this.rotate(RIGHT).rotate(RIGHT) -> 2
            else -> error("Wrong direction")
        }
    }

    operator fun component1() = dx

    operator fun component2() = dy

    companion object {
        fun byFirstLetter(letter: Char): Direction =
            Direction.entries.take(4).find { it.name.startsWith(letter.uppercaseChar()) }!!

        val ARROWS = setOf('>', '<', '^', 'v')

        fun byArrow(arrow: Char): Direction = when (arrow) {
            '>' -> RIGHT
            '<' -> LEFT
            '^' -> UP
            'v' -> DOWN
            else -> error("Unknown direction: $arrow")
        }

        val SQUARE = listOf(UP, RIGHT, LEFT, DOWN)
    }

}
