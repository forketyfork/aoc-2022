package year2015

class Day07 {

    class Node(val name: String) {
        var op: ((Long?, Long?) -> Long) = { _, _ -> name.toLong() }
        var left: Node? = null
        var right: Node? = null
        val value: Long by lazy { op(left?.value, right?.value) }
    }

    private fun buildGraph(input: String) = buildMap {
        input.lines().forEach { line ->
            val (source, dest) = line.split(" -> ")
            val node = computeIfAbsent(dest, ::Node)
            if (source.startsWith("NOT ")) {
                node.left = computeIfAbsent(source.substringAfter("NOT "), ::Node)
                node.op = { a, _ -> a!!.toUShort().inv().toLong() }
            } else {
                if (source.contains(' ')) {
                    val (left, operation, right) = source.split(" ")
                    node.left = computeIfAbsent(left, ::Node)
                    if (operation == "AND" || operation == "OR") {
                        node.right = computeIfAbsent(right, ::Node)
                    }
                    node.op = when (operation) {
                        "AND" -> { a, b -> a!! and b!! }
                        "OR" -> { a, b -> a!! or b!! }
                        "LSHIFT" -> { a, _ -> a!! shl right.toInt() }
                        "RSHIFT" -> { a, _ -> a!! shr right.toInt() }
                        else -> error("Unknown operation: $operation")
                    }
                } else if (source.toLongOrNull() != null) {
                    node.op = { _, _ -> source.toLong() }
                } else {
                    node.left = computeIfAbsent(source, ::Node)
                    node.op = { a, _ -> a!! }
                }
            }
        }
    }

    fun part1(input: String, nodeName: String) = buildGraph(input)[nodeName]!!.value

    fun part2(input: String): Long {
        val a = buildGraph(input)["a"]!!.value
        val graph = buildGraph(input)
        graph["b"]?.op = { _, _ -> a }
        return graph["a"]!!.value
    }

}