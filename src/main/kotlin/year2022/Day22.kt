package year2022

import utils.Direction
import utils.Direction.*
import utils.Point2D

class Day22(contents: String) {

    data class State(val position: Point2D, val facing: Direction)

    private fun List<String>.at(position: Point2D) = this[position.y][position.x]

    private val width: Int

    private val maze = contents.lines().dropLast(2).also {
        width = it.maxOf(String::length)
    }.map {
        " " + it.padEnd(width + 1)
    }.let {
        listOf(" ".repeat(width + 2))
            .plus(it)
            .plus(" ".repeat(width + 2))
    }

    private val directions = mutableListOf<Any>()

    init {
        var number = 0
        contents.lines().last().forEach { char ->
            number = when (char) {
                'L' -> {
                    directions.add(number)
                    directions.add(LEFT)
                    0
                }

                'R' -> {
                    directions.add(number)
                    directions.add(RIGHT)
                    0
                }

                else -> number * 10 + (char - '0')
            }
        }
        directions.add(number)
    }

    private val wraparoundRulesSimple: Map<Direction, Pair<State.() -> Point2D, State.() -> Direction>> = mapOf(
        LEFT to Pair({ position.copy(x = maze[position.y].indexOfLast { it != ' ' }) }, { facing }),
        RIGHT to Pair({ position.copy(x = maze[position.y].indexOfFirst { it != ' ' }) }, { facing }),
        UP to Pair({ position.copy(y = maze.indexOfLast { it[position.x] != ' ' }) }, { facing }),
        DOWN to Pair({ position.copy(y = maze.indexOfFirst { it[position.x] != ' ' }) }, { facing }),
    )

    private val wraparoundRulesCube4: Map<Direction, Pair<State.() -> Point2D, State.() -> Direction>> = mapOf(
        LEFT to Pair({
            when (position.y) {
                in (1..4) -> Point2D(4 + position.y, 5)
                in (5..8) -> Point2D(21 - position.y, 12)
                in (9..12) -> Point2D(17 - position.y, 8)
                else -> error(position)
            }
        }, {
            when (position.y) {
                in (1..4) -> {
                    DOWN
                }

                in (5..8) -> {
                    UP
                }

                in (9..12) -> {
                    UP
                }

                else -> error(position)
            }
        }),
        RIGHT to Pair({
            when (position.y) {
                in (1..4) -> Point2D(13 - position.y, 16)
                in (5..8) -> Point2D(21 - position.y, 9)
                in (9..12) -> Point2D(12, 13 - position.y)
                else -> error(position)
            }
        }, {
            when (position.y) {
                in (1..4) -> {
                    LEFT
                }

                in (5..8) -> {
                    DOWN
                }

                in (9..12) -> {
                    LEFT
                }

                else -> error(position)
            }
        }),
        UP to Pair({
            when (position.x) {
                in (1..4) -> Point2D(13 - position.x, 1)
                in (5..8) -> Point2D(9, position.x - 4)
                in (9..12) -> Point2D(13 - position.x, 5)
                in (13..16) -> Point2D(21 - position.x, 12)
                else -> error(position)
            }
        }, {
            when (position.x) {
                in (1..4) -> {
                    DOWN
                }

                in (5..8) -> {
                    RIGHT
                }

                in (9..12) -> {
                    DOWN
                }

                in (13..16) -> {
                    LEFT
                }

                else -> error(position)
            }
        }),
        DOWN to Pair({
            when (position.x) {
                in (1..4) -> Point2D(13 - position.x, 1)
                in (5..8) -> Point2D(9, 17 - position.x)
                in (9..12) -> Point2D(13 - position.x, 8)
                in (13..16) -> Point2D(1, 21 - position.x)
                else -> error(position)
            }
        }, {
            when (position.x) {
                in (1..4) -> {
                    DOWN
                }

                in (5..8) -> {
                    RIGHT
                }

                in (9..12) -> {
                    UP
                }

                in (13..16) -> {
                    RIGHT
                }

                else -> error(position)
            }
        }),
    )

    private val wraparoundRulesCube50: Map<Direction, Pair<State.() -> Point2D, State.() -> Direction>> = mapOf(
        LEFT to Pair({
            when (position.y) {
                in (1..50) -> Point2D(1, 151 - position.y)
                in (51..100) -> Point2D(position.y - 50, 101)
                in (101..150) -> Point2D(51, 151 - position.y)
                in (151..200) -> Point2D(position.y - 100, 1)
                else -> error(position)
            }
        }, {
            when (position.y) {
                in (1..50) -> {
                    RIGHT
                }

                in (51..100) -> {
                    DOWN
                }

                in (101..150) -> {
                    RIGHT
                }

                in (150..200) -> {
                    DOWN
                }

                else -> error(position)
            }
        }),
        RIGHT to Pair({
            when (position.y) {
                in (1..50) -> Point2D(100, 151 - position.y)
                in (51..100) -> Point2D(position.y + 50, 50)
                in (101..150) -> Point2D(150, 151 - position.y)
                in (151..200) -> Point2D(position.y - 100, 150)
                else -> error(position)
            }
        }, {
            when (position.y) {
                in (1..50) -> {
                    LEFT
                }

                in (51..100) -> {
                    UP
                }

                in (101..150) -> {
                    LEFT
                }

                in (151..200) -> {
                    UP
                }

                else -> error(position)
            }
        }),
        UP to Pair({
            when (position.x) {
                in (1..50) -> Point2D(51, 50 + position.x)
                in (51..100) -> Point2D(1, 100 + position.x)
                in (101..150) -> Point2D(position.x - 100, 200)
                else -> error(position)
            }
        }, {
            when (position.x) {
                in (1..50) -> {
                    RIGHT
                }

                in (51..100) -> {
                    RIGHT
                }

                in (101..150) -> {
                    UP
                }

                else -> error(position)
            }
        }),
        DOWN to Pair({
            when (position.x) {
                in (1..50) -> Point2D(position.x + 100, 1)
                in (51..100) -> Point2D(50, position.x + 100)
                in (101..150) -> Point2D(100, position.x - 50)
                else -> error(position)
            }
        }, {
            when (position.x) {
                in (1..50) -> {
                    DOWN
                }

                in (51..100) -> {
                    LEFT
                }

                in (101..150) -> {
                    LEFT
                }

                else -> error(position)
            }
        })
    )

    private fun move(
        state: State,
        steps: Int,
        wraparoundRules: (Direction) -> Pair<State.() -> Point2D, State.() -> Direction>
    ): State =
        with(state) {
            if (steps == 0) {
                this
            } else {

                val nextPosition = position.move(state.facing)
                move(when (maze.at(nextPosition)) {
                    ' ' -> wraparoundRules(state.facing).let { (wraparoundPosition, wraparoundFacing) ->
                        with(wraparoundPosition.invoke(state)) {
                            if (maze.at(this) == '#') {
                                state
                            } else {
                                state.copy(position = this, facing = wraparoundFacing.invoke(state))
                            }
                        }
                    }

                    '#' -> this
                    else -> copy(position = nextPosition)
                }, steps - 1, wraparoundRules)
            }
        }

    fun part1() = solve(wraparoundRulesSimple)

    fun part2(cubeSide: Int) = when (cubeSide) {
        4 -> solve(wraparoundRulesCube4)
        50 -> solve(wraparoundRulesCube50)
        else -> error("Can't solve for cube side $cubeSide")
    }

    private fun solve(wraparoundRules: Map<Direction, Pair<State.() -> Point2D, State.() -> Direction>>): Long {
        var state = State(Point2D(maze[1].indexOf('.'), 1), RIGHT)
        directions.forEach { direction ->
            when (direction) {
                is Int -> state = move(state, direction, wraparoundRules::getValue)
                is Direction -> state = state.copy(facing = state.facing.rotate(direction))
            }
        }

        return 1000L * state.position.y + 4 * state.position.x + when (state.facing) {
            RIGHT -> 0
            DOWN -> 1
            LEFT -> 2
            UP -> 3
            else -> error(state.facing)
        }
    }
}
