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
    private val source = Point2D(1, 0)
    private val destination = Point2D(width - 2, height - 1)
    private val blizzardPeriod = (width - 2).lcm(height - 2)

    private val blizzardsMap = mapOf(
        0 to lines.flatMapIndexed { row, line ->
            line.withIndex().filter { it.value in Direction.ARROWS }.map { (col, char) ->
                Point2D(col, row) to setOf(Blizzard(Point2D(col, row), Direction.byArrow(char)))
            }

        }.toMap()
    ).toMutableMap()

    private fun solve(initialState: State, dest: Point2D): Int = with(ArrayDeque<State>()) {
        val seenStates = mutableMapOf<Point2D, MutableSet<Int>>()
        add(initialState)
        while (!isEmpty()) {
            val state = removeFirst()
            if (state.position == dest) {
                return state.step - 1
            }
            if (!seenStates.getOrPut(state.position) { mutableSetOf() }.add(state.step % blizzardPeriod)) {
                continue
            }

            val nextBlizzards = blizzardsMap.computeIfAbsent(state.step % blizzardPeriod) {
                blizzardsMap[state.step - 1]!!.values.flatMap { blizzards ->
                    blizzards.map { blizzard ->
                        val nextPosition = blizzard.position.move(blizzard.direction).let {
                            if (it.x == width - 1) {
                                it.copy(x = 1)
                            } else if (it.x == 0) {
                                it.copy(x = width - 2)
                            } else if (it != dest && it.y == height - 1 || it == dest) {
                                it.copy(y = 1)
                            } else if (it != source && it.y == 0 || it == source) {
                                it.copy(y = height - 2)
                            } else {
                                it
                            }
                        }
                        nextPosition to blizzard.copy(position = nextPosition)
                    }
                }.groupBy({ it.first }, { it.second })
                    .map { it.key to it.value.toSet() }
                    .toMap()
            }
            sequenceOf(DOWN, LEFT, UP, RIGHT)
                .map { dir -> state.position.move(dir) }
                .filter {
                    it == dest || it.x in (1..width - 2) && it.y in (1..height - 2)
                }
                .plus(state.position)
                .filter { !nextBlizzards.keys.contains(it) }
                .forEach { nextPosition -> addLast(State(state.step + 1, nextPosition)) }
        }
        error("Solution not found")
    }

    fun part1() = solve(State(0, source), destination)

    fun part2() = solve(
        State(
            solve(
                State(
                    solve(
                        State(
                            0,
                            source
                        ),
                        destination
                    ),
                    destination
                ), source
            ), source
        ),
        destination
    )

}
