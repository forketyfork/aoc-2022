package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D

class Day12 {

    fun part1(input: String): Long {
        val grid = CharGrid(input)
        return grid.pointSequence()
            .filter(grid::isClear)
            .sumOf { start ->
                var perimeter = 0L
                var area = 1L
                grid.bfs(
                    start = start,
                    canMove = { from, to -> grid[from] == grid[to] },
                    onCell = { area++ },
                    onWall = { _, _ -> perimeter++ }
                )
                perimeter * area
            }
    }


    class Wall(firstCell: Point2D, val direction: Direction) {

        private var first: Int = when (direction) {
            Direction.UP, Direction.DOWN -> firstCell.x
            Direction.RIGHT, Direction.LEFT -> firstCell.y
            else -> error("Wrong direction: $direction")
        }

        private var last: Int = first

        private val other: Int = when (direction) {
            Direction.UP, Direction.DOWN -> firstCell.y
            Direction.RIGHT, Direction.LEFT -> firstCell.x
            else -> error("Wrong direction: $direction")
        }

        fun tryAdd(cell: Point2D, dir: Direction): Boolean {
            if (dir != direction) {
                return false
            }

            if (when (dir) {
                    Direction.UP, Direction.DOWN -> other == cell.y && first == cell.x + 1
                    Direction.LEFT, Direction.RIGHT -> other == cell.x && first == cell.y + 1
                    else -> error("Wrong direction: $direction")
                }
            ) {
                first--
                return true
            }
            if (when (dir) {
                    Direction.UP, Direction.DOWN -> other == cell.y && last == cell.x - 1
                    Direction.LEFT, Direction.RIGHT -> other == cell.x && last == cell.y - 1
                    else -> error("Wrong direction: $direction")
                }
            ) {
                last++
                return true
            }
            return false
        }

        fun merge(wall: Wall) {
            assert(direction == wall.direction && other == wall.other) { "Trying to merge non-adjacent walls: $wall, $this" }
            if (first == wall.last) {
                first = wall.first
            } else if (last == wall.first) {
                last = wall.last
            } else {
                error("$wall can't be merged into $this")
            }
        }

    }

    fun part2(input: String): Long {
        val grid = CharGrid(input)
        return grid.pointSequence()
            .filter(grid::isClear)
            .sumOf { start ->
                var area = 1L
                val walls = buildList<Wall> {
                    grid.bfs(
                        start = start,
                        canMove = { from, to -> grid[from] == grid[to] },
                        onCell = { area++ },
                        onWall = { cell, direction ->
                            val matchingWalls = filter { it.tryAdd(cell, direction) }
                            when (matchingWalls.size) {
                                0 -> add(Wall(cell, direction)) // didn't find a wall, creating a new one
                                1 -> {} // found a wall, extended it
                                2 -> { // found 2 walls, need to join them
                                    remove(matchingWalls[1])
                                    matchingWalls[0].merge(matchingWalls[1])
                                }

                                else -> error("Something's wrong, cell passes into ${matchingWalls.size} walls")
                            }
                        }
                    )
                }
                walls.size * area
            }
    }

}