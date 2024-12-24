package year2024

import year2024.Day24.Node.Leaf
import year2024.Day24.Node.Op

class Day24 {

    companion object {
        val REGEX = "(\\w+)\\s(\\w+)\\s(\\w+)\\s->\\s(\\w+)".toRegex()
    }

    sealed interface Node {

        val value: Boolean
        val name: String

        class Op(override val name: String) : Node {
            lateinit var node1: Node
            lateinit var node2: Node
            lateinit var op: ((Boolean, Boolean) -> Boolean)

            override val value by lazy { op(node1.value, node2.value) }
        }

        class Leaf(override val name: String, override val value: Boolean) : Node
    }

    fun part1(input: String): Long {
        val (leavesPart, opsPart) = input.split("\n\n")
        return buildMap<String, Node> {

            leavesPart.lines().forEach {
                val (name, valueString) = it.split(": ")
                put(name, Leaf(name, valueString == "1"))
            }

            opsPart.lines().forEach {
                val (r1, op, r2, r3) = REGEX.find(it)!!.groups.toList().drop(1).map { it!!.value }
                val r3Node = computeIfAbsent(r3, ::Op) as Op
                r3Node.op = when (op) {
                    "XOR" -> Boolean::xor
                    "AND" -> Boolean::and
                    "OR" -> Boolean::or
                    else -> error("Unknown op: $op")
                }
                r3Node.node1 = computeIfAbsent(r1, ::Op)
                r3Node.node2 = computeIfAbsent(r2, ::Op)
            }

        }.values.filter { it.name[0] == 'z' }
            .sortedBy { it.name }
            .foldIndexed(0) { idx, acc, node ->
                if (node.value) {
                    acc xor (1L shl idx)
                } else {
                    acc
                }
            }
    }

    fun part2(input: String): String {
        return ""
    }

}
