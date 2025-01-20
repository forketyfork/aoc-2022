package year2015

class Day09 {

    companion object {
        val REGEX = "(.+)\\sto\\s(.+)\\s=\\s(\\d+)".toRegex()
    }

    class Node(val name: String, val children: MutableMap<Node, Int> = mutableMapOf())

    fun part1(input: String) = solve(input, Iterable<Int>::min)
    fun part2(input: String) = solve(input, Iterable<Int>::max)

    fun solve(input: String, aggregator: Iterable<Int>.() -> Int) = buildMap {
        input.lines().forEach { line ->
            val (city1, city2, distance) = REGEX.find(line)?.groupValues?.drop(1) ?: error("parse error")
            val distanceNum = distance.toInt()
            val city1Node = getOrPut(city1) { Node(city1) }
            val city2Node = getOrPut(city2) { Node(city2) }
            city1Node.children[city2Node] = distanceNum
            city2Node.children[city1Node] = distanceNum
        }
    }.bestPath(aggregator)


    fun Map<String, Node>.bestPath(
        aggregator: Iterable<Int>.() -> Int,
        children: Map<Node, Int> = values.associateWith { 0 },
        sum: Int = 0
    ): Int {
        if (isEmpty()) {
            return sum
        }
        return children.filterKeys { it.name in keys }
            .map { (node, distance) -> (this - node.name).bestPath(aggregator, node.children, sum + distance) }
            .aggregator()
    }

}
