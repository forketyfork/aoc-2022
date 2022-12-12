package utils

enum class Direction(private val dx: Int, private val dy: Int) {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1);

    operator fun component1() = dx

    operator fun component2() = dy

    companion object {
        fun byFirstLetter(letter: Char): Direction =
            Direction.values().find { it.name.startsWith(letter.uppercaseChar()) }!!
    }

}
