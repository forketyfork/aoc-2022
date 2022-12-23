package year2015

// TODO WIP
class Day07 {


    class Node(val name: String) {

        var actualValue: Long? = null
        var op: ((Long, Long?) -> Long)? = null
        var left: Node? = null
        var right: Node? = null
        fun value(): Long = actualValue ?: op!!(left!!.value(), right?.value())

    }

    private fun buildGraph(input: String) = buildMap<String, Node> {
        input.lines().forEach { line ->
            val (source, dest) = line.split(" -> ")
            val node = computeIfAbsent(dest, ::Node)
            node.actualValue = if (source[0].isDigit()) source.toLong() else null.also {
                if (source.startsWith("NOT ")) {
                    node.left = computeIfAbsent(source.substringAfter("NOT "), ::Node)
                    node.op = { a, _ -> a.toUShort().inv().toLong() }
                } else {
                    if (source.contains(' ')) {
                        val (left, operation, right) = source.split(" ")
                        node.left = computeIfAbsent(left, ::Node)
                        if (operation == "AND" || operation == "OR") {
                            node.right = computeIfAbsent(right, ::Node)
                        }
                        node.op = when (operation) {
                            "AND" -> { a, b -> a and b!! }
                            "OR" -> { a, b -> a or b!! }
                            "LSHIFT" -> { a, _ -> a shl right.toInt() }
                            "RSHIFT" -> { a, _ -> a shr right.toInt() }
                            else -> error("Unknown operation: $operation")
                        }
                    } else {
                        node.left = computeIfAbsent(source, ::Node)
                        node.op = {a, _ -> a}
                    }
                }
            }
        }
    }

    fun part1(input: String, nodeName: String) = buildGraph(input)[nodeName]!!.value()

    fun part2(input: String) = 0

}