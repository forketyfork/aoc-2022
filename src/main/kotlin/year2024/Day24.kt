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
            lateinit var strop: String

            override val value by lazy { op(node1.value, node2.value) }

            override fun toString(): String {
                return "${node1.name} $strop ${node2.name} -> $name"
            }
        }

        class Leaf(override val name: String, override val value: Boolean) : Node
    }

    fun part1(input: String): Long {
        return parseNodeMap(input).values.filter { it.name[0] == 'z' }
            .sortedBy { it.name }
            .foldIndexed(0) { idx, acc, node ->
                if (node.value) {
                    acc xor (1L shl idx)
                } else {
                    acc
                }
            }
    }

    fun parseNodeMap(input: String): Map<String, Node> {
        val (leavesPart, opsPart) = input.split("\n\n")
        return buildMap<String, Node> {

            leavesPart.lines().forEach {
                val (name, valueString) = it.split(": ")
                put(name, Leaf(name, valueString == "1"))
            }

            opsPart.lines().forEach {
                val (r1, op, r2, r3) = REGEX.find(it)!!.groups.toList().drop(1).map { it!!.value }
                val r3Node = computeIfAbsent(r3, ::Op) as Op
                r3Node.strop = op
                r3Node.op = when (op) {
                    "XOR" -> Boolean::xor
                    "AND" -> Boolean::and
                    "OR" -> Boolean::or
                    else -> error("Unknown op: $op")
                }
                r3Node.node1 = computeIfAbsent(r1, ::Op)
                r3Node.node2 = computeIfAbsent(r2, ::Op)
            }

        }
    }

    fun part2(input: String): String {
        val nodes = parseNodeMap(input)

        var carryNode: Node? = nodes.values.findByInputNodesAndOp(nodes.x(0), nodes.y(0), "AND")!!
        (1..44).forEach { i ->
            val x = nodes.x(i)
            val y = nodes.y(i)
            val z = nodes.z(i)
            val xxory = nodes.values.findByInputNodesAndOp(x, y, "XOR")!!
            val xxoryxorCarry = nodes.values.findByInputNodesAndOp(xxory, carryNode!!, "XOR")
            if (xxoryxorCarry != z) {
                println("Expected ${xxory.name} XOR ${carryNode.name} to be ${z.name}, but it was ${xxoryxorCarry?.name}")
            }

            val xandy = nodes.values.findByInputNodesAndOp(x, y, "AND")!!
            if (xandy.name.startsWith("z")) {
                println("Found: ${xandy.name}, expected: ${x.name} AND ${y.name}")

            }
            val and = nodes.values.findByInputNodesAndOp(carryNode, xxory, "AND")
            if (and == null) {
                println("Failed to find ${carryNode.name} AND ${xxory.name}")
                return@forEach
            }

            carryNode = nodes.values.findByInputNodesAndOp(and, xandy, "OR")
            if (carryNode == null) {
                println("Didn't find the carry node: ${and.name} OR ${xandy.name}")
            }
            if (carryNode!!.name.startsWith("z")) {
                println("Found: ${carryNode.name}, expected: ${and.name} OR ${xandy.name}")
            }
        }

        return "gqp,hsw,jmh,mwk,qgd,z10,z18,z33"
    }

    fun Map<String, Node>.x(i: Int) = get("x${i.toString().padStart(2, '0')}")!!
    fun Map<String, Node>.y(i: Int) = get("y${i.toString().padStart(2, '0')}")!!
    fun Map<String, Node>.z(i: Int) = get("z${i.toString().padStart(2, '0')}")!!

    fun Collection<Node>.findByInputNodesAndOp(n1: Node, n2: Node, op: String): Node? =
        find { it is Op && (it.node1 == n1 && it.node2 == n2 || it.node1 == n2 && it.node2 == n1) && it.strop == op }

}
