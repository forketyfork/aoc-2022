package year2022

import kotlin.math.min

class Node(val elevation: Int) {
    var distance: Int = Int.MAX_VALUE
    var visited: Boolean = false
    var enqueued: Boolean = false
    var neighbors = mutableListOf<Node>()
}

class Day12 {

    private val steps = arrayOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

    private fun bfs(
        input: String,
        sElevation: Int,
        startNodeSelector: (Node) -> Boolean,
        neighboringCondition: (Node, Node) -> Boolean
    ): List<Node> {
        val lines = input.lines()
        var startNode: Node? = null

        val nodeArray = Array(lines.size) { row ->
            lines[row].map { char ->
                Node(
                    when (char) {
                        'S' -> sElevation
                        'E' -> 26
                        else -> char - 'a'
                    }
                ).also {
                    if (startNodeSelector(it)) {
                        startNode = it
                        it.distance = 0
                    }
                }
            }
        }

        nodeArray.forEachIndexed { row, nodes ->
            nodes.forEachIndexed { col, node ->
                steps.forEach { (dx, dy) ->
                    val row1 = row + dx
                    val col1 = col + dy
                    if (row1 in nodeArray.indices && col1 in nodeArray[0].indices
                        && neighboringCondition(nodeArray[row1][col1], nodeArray[row][col])
                    ) {
                        node.neighbors.add(nodeArray[row1][col1])
                    }
                }
            }
        }

        val queue = MutableList(1) { startNode!! }
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (next in current.neighbors) {
                if (!next.visited) {
                    queue.enqueue(next)
                    next.distance = min(next.distance, current.distance + 1)
                }
            }
            current.visited = true
        }
        return nodeArray.flatMap { it }
    }

    private fun MutableList<Node>.enqueue(node: Node) {
        if (!node.enqueued) {
            add(node)
            node.enqueued = true
        }
    }

    fun part1(input: String) = bfs(
        input,
        -1,
        { it.elevation == -1 },
        { next, prev -> next.elevation - prev.elevation <= 1 })
        .find { it.elevation == 26 }!!.distance

    fun part2(input: String) = bfs(
        input,
        0,
        { it.elevation == 26 },
        { next, prev -> next.elevation - prev.elevation >= -1 })
        .filter { it.elevation == 0 }
        .minOf { it.distance }

}