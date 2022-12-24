package year2022

import utils.Direction
import utils.Direction.*
import utils.Point2D
import utils.lcm

class Day24(contents: String) {

    data class Blizzard(val position: Point2D, val direction: Direction)
    data class State(val step: Int, val position: Point2D)

    private val lines = contents.lines()
    private val width = lines.first().length
    private val height = lines.size
    private val entrance = Point2D(1, 0)
    private val exit = Point2D(width - 2, height - 1)
    private val blizzardPeriod = (width - 2).lcm(height - 2)
    private val blizzardsByStep = mapOf(
        0 to lines.flatMapIndexed { row, line ->
            line.withIndex().filter { it.value in Direction.ARROWS }.map { (col, char) ->
                Point2D(col, row) to listOf(Blizzard(Point2D(col, row), Direction.byArrow(char)))
            }
        }.toMap()
    ).toMutableMap()

    fun part1() = solve(State(0, entrance), exit)

    fun part2() = listOf(entrance, exit, entrance, exit)
        .windowed(2)
        .fold(0) { step, (from, to) -> solve(State(step, from), to) }

    private fun solve(initialState: State, destination: Point2D): Int = with(ArrayDeque(setOf(initialState))) {
        val seenStates = mutableMapOf<Point2D, MutableSet<Int>>()
        while (!isEmpty()) {
            val state = removeFirst()
            if (state.position == destination) {
                return state.step - 1
            }
            if (seenStates.getOrPut(state.position) { mutableSetOf() }.add(state.step % blizzardPeriod)) {
                val nextBlizzards = blizzardsByStep.computeIfAbsent(state.step % blizzardPeriod) {
                    blizzardsByStep[state.step % blizzardPeriod - 1]!!.values.flatMap { blizzards ->
                        blizzards.map { blizzard ->
                            val nextPosition = blizzard.position.move(blizzard.direction).let {
                                if (it.x == width - 1) {
                                    it.copy(x = 1)
                                } else if (it.x == 0) {
                                    it.copy(x = width - 2)
                                } else if (it != exit && it.y == height - 1 || it == exit) {
                                    it.copy(y = 1)
                                } else if (it != entrance && it.y == 0 || it == entrance) {
                                    it.copy(y = height - 2)
                                } else {
                                    it
                                }
                            }
                            nextPosition to blizzard.copy(position = nextPosition)
                        }
                    }.groupBy({ it.first }, { it.second }).toMap()
                }
                sequenceOf(DOWN, LEFT, UP, RIGHT)
                    .map { dir -> state.position.move(dir) }
                    .filter {
                        it == entrance || it == exit || it.x in (1..width - 2) && it.y in (1..height - 2)
                    }
                    .plus(state.position)
                    .filter { !nextBlizzards.keys.contains(it) }
                    .forEach { nextPosition -> addLast(State(state.step + 1, nextPosition)) }
            }
        }
        error("Solution not found")
    }

}
