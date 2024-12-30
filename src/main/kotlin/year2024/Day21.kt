package year2024

import utils.CharGrid
import utils.Direction
import utils.Point2D

class Day21 {

    fun MutableMap<Pair<Char, Char>, String>.bfs(grid: CharGrid, start: Point2D) {
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
            if (point != start && grid[point] != '#') {
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
                    grid[start] to grid[point],
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

        println("Max memory: ${Runtime.getRuntime().maxMemory()}")


        val numGrid = CharGrid("789\n456\n123\n#0A")
        val arrowGrid = CharGrid("#^A\n<v>")

        val numPaths = buildMap<Pair<Char, Char>, String> {
            numGrid.pointSequence().forEach { point ->
                if (numGrid[point] != '#') bfs(numGrid, point)
            }
        }

        val arrowPaths = buildMap<Pair<Char, Char>, String> {
            arrowGrid.pointSequence().forEach { point ->
                if (arrowGrid[point] != '#') bfs(arrowGrid, point)
            }
        }

        return input.lines().sumOf { code ->
            (0..<robots).fold(path(code, numPaths)) { code, iteration ->
                println("Iteration: $iteration, code size: ${code.length}")
                path(code, arrowPaths)
            }.length * code.removeSuffix("A").toLong()
        }
    }

    fun path(code: String, paths: Map<Pair<Char, Char>, String>): String {
        val sb = StringBuilder()
        var ch1 = 'A'
        for (i in (0..code.lastIndex)) {
            val ch2 = code[i]
            if (ch1 != ch2) {
                sb.append(paths[ch1 to ch2]!!)
            }
            sb.append('A')
            ch1 = ch2
        }
        return sb.toString()
    }

}
