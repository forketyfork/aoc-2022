package year2024

import utils.*

class Day12 {

    fun part1(input: String): Long {
        val grid = CharGrid(input)
        return grid.points()
            .filter(grid::isClear)
            .map { startingPoint ->
                var perimeter = 0L
                var area = 0L
                val queue = ArrayDeque<Point2D>()
                val ch = grid[startingPoint]
                queue.add(startingPoint)
                grid.mark(startingPoint)
                while (queue.isNotEmpty()) {
                    val element = queue.removeFirst()
                    area++
                    Direction.SQUARE.forEach { dir ->
                        val neighbor = element.move(dir)
                        if (grid[neighbor] == ch) {
                            if (grid.isClear(neighbor)) {
                                queue.add(neighbor)
                                grid.mark(neighbor)
                            }
                        } else {
                            perimeter++
                        }
                    }
                }
                perimeter to area
            }.sumOf { it.first * it.second }
    }

    fun part2(input: String): Long {
        return 0L
    }

}