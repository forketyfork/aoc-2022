package utils

enum class Direction(private val dx: Int, private val dy: Int) {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT_UP(-1, -1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1, 1),
    LEFT_DOWN(-1, 1);

    operator fun component1() = dx

    operator fun component2() = dy

    companion object {
        fun byFirstLetter(letter: Char): Direction =
            Direction.values().find { it.name.startsWith(letter.uppercaseChar()) }!!

        fun byArrow(arrow: Char): Direction = when (arrow) {
            '>' -> RIGHT
            '<' -> LEFT
            '^' -> UP
            'v' -> DOWN
            else -> error("Unknown direction: $arrow")
        }
    }

}
