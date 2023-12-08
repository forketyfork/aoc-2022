package year2023

import utils.lcm

class Day08 {

    fun part1(input: String) = solve(input, { it == "AAA" }, { it == "ZZZ" })
    fun part2(input: String) = solve(input, { it.endsWith('A') }, { it.endsWith('Z') })

    data class Node(val left: String, val right: String, val terminal: Boolean)

    fun solve(input: String, isSource: (String) -> Boolean, isDestination: (String) -> Boolean): Long {
        val lines = input.lines()
        val path = lines.first()
        val nodes = lines.drop(2).associate { line ->
            val (name, left, right) = line.split(*" =,()".toCharArray()).filter(String::isNotBlank)
            name to Node(left, right, isDestination(name))
        }
        return nodes.filter { isSource(it.key) }.map { node ->
            (1..Long.MAX_VALUE).fold(node.value) { node, iteration ->
                path.fold(node) { node, dir -> nodes[(if (dir == 'L') node.left else node.right)]!! }
                    .apply {
                        if (terminal) {
                            return@map iteration
                        }
                    }
            }
            0
        }.reduce(Long::lcm) * path.length
    }

}
