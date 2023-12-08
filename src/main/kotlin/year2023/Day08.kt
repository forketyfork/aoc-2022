package year2023

import utils.lcm

class Day08 {

    class Node(val name: String, val leftString: String, val rightString: String, val terminal: Boolean) {
        var left: Node? = null
        var right: Node? = null
    }

    fun part1(input: String) = solve(input, { it == "AAA" }, { it == "ZZZ" })
    fun part2(input: String) = solve(input, { it.endsWith('A') }, { it.endsWith('Z') })

    fun solve(input: String, startingCondition: (String) -> Boolean, terminalCondition: (String) -> Boolean): Long {
        val lines = input.lines()
        val path = lines.first()
        val nodeMap = mutableMapOf<String, Node>()
        val startNodes = mutableListOf<Node>()
        lines.drop(2).map { line ->
            val name = line.substring(0, 3)
            val node = Node(name, line.substring(7, 10), line.substring(12, 15), terminalCondition(name))
            nodeMap[name] = node
            if (startingCondition(name)) {
                startNodes += node
            }
        }
        nodeMap.values.forEach { value ->
            value.left = nodeMap[value.leftString]
            value.right = nodeMap[value.rightString]
        }
        return startNodes.map { node ->
            var currentNode = node
            for (idx in 0..Long.MAX_VALUE) {
                val dir = path[(idx % path.length).toInt()]
                currentNode = if (dir == 'R') {
                    currentNode.right!!
                } else {
                    currentNode.left!!
                }
                if (currentNode.terminal) {
                    return@map idx + 1
                }
            }
            0
        }.reduce(Long::lcm)
    }

}
