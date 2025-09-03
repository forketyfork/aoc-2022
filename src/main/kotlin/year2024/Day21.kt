package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D

class Day21 {

    fun MutableMap<String, String>.bfs(grid: CharGrid, start: Point2D) {
        val block = grid.findFirst('#')

        grid.resetMarks()

        val parentMap = mutableMapOf<Point2D, Pair<Point2D, Direction>>()
        val queue = ArrayDeque<Point2D>()

        queue.add(start)
        grid.mark(start)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            Direction.SQUARE.forEach { dir ->
                val neighbour = node.move(dir)
                if (grid.inBounds(neighbour) && grid[neighbour] != '#' && grid.isClear(neighbour)) {
                    parentMap[neighbour] = node to dir
                    grid.mark(neighbour)
                    queue.add(neighbour)
                }
            }
        }

        grid.pointSequence().forEach { point ->
            if (grid[point] != '#') {
                val list = mutableListOf<Direction>()
                var curr: Point2D? = point
                while (curr != null) {
                    val (prev, dir) = parentMap[curr] ?: (null to null)
                    if (dir != null) {
                        list.add(dir)
                    }
                    curr = prev
                }

                val list1 = list.sorted()
                val list2 = list1.reversed()

                put(
                    "${grid[start]}${grid[point]}",
                    buildList {
                        if (!list1.passesThrough(start, block)) add(list1.joinToString(""))
                        if (list2 != list1 && !list2.passesThrough(start, block)) add(list2.joinToString(""))
                        if (size == 2 && list1.distinct().size > 1) {
                            if (first().startsWith("^") || first().startsWith(">")) {
                                removeFirst()
                            } else {
                                removeAt(1)
                            }
                        }
                    }.first()
                )
            }
        }
    }

    fun List<Direction>.passesThrough(start: Point2D, point: Point2D): Boolean {
        var curr = start
        forEach { dir ->
            curr = curr.move(dir)
            if (curr == point) {
                return true
            }
        }
        return false
    }

    fun part1(input: String) = solve(input, 2)
    fun part2(input: String) = solve(input, 25)

    fun solve(input: String, robots: Int): Long {

        val numGrid = CharGrid("789\n456\n123\n#0A")
        val arrowGrid = CharGrid("#^A\n<v>")

        val numPaths = buildMap {
            numGrid.pointSequence().forEach { point ->
                if (numGrid[point] != '#') bfs(numGrid, point)
            }
        }

        val arrowPaths = buildMap {
            arrowGrid.pointSequence().forEach { point ->
                if (arrowGrid[point] != '#') bfs(arrowGrid, point)
            }
        }

        return input.lines().sumOf { code ->
            ((0..<robots).fold(path(code.toPairCounts(), numPaths)) { code, _ ->
                path(code, arrowPaths)
            }.values.sum()) * code.removeSuffix("A").toLong()
        }
    }

    fun path(pairCounts: Map<String, Long>, paths: Map<String, String>): Map<String, Long> {
        return pairCounts.map { (pair, count) ->
            (paths[pair]!! + "A").toPairCounts().mapValues { it.value * count }
        }.reduce { m1, m2 -> (m1.keys + m2.keys).toSet().associateWith { key -> (m1[key] ?: 0L) + (m2[key] ?: 0L) } }
    }

    fun String.toPairCounts(): Map<String, Long> =
        "A$this".windowed(2).groupBy { it }.mapValues { it.value.size.toLong() }

}
