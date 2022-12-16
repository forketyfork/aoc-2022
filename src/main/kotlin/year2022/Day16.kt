package year2022

data class ValveNode(val name: String) {
    var flowRate = 0
    var neighbors = mutableListOf<ValveNode>()
    val distanceMap: MutableMap<ValveNode, Int> = mutableMapOf()
}

class Day16(input: String) {

    private val regex = """Valve (..) has flow rate=(\d*); tunnels? leads? to valves? (.*)""".toRegex()

    private val nodes = with(mutableMapOf<String, ValveNode>()) {
        regex.findAll(input).toList().map { match ->
            val valveId = match.groupValues[1]
            getOrPut(valveId) { ValveNode(valveId) }.apply {
                this.flowRate = match.groupValues[2].toInt()
                match.groupValues[3].split(", ").forEach { neighborId ->
                    this.neighbors.add(getOrPut(neighborId) { ValveNode(neighborId) })
                }
            }
        }
    }

    init {
        nodes.filter { it.flowRate > 0 }.forEach { root ->
            root.distanceMap[root] = 0
            with(ArrayDeque<ValveNode>()) {
                add(root)
                val visited = mutableSetOf<ValveNode>()
                while (isNotEmpty()) {
                    val current = removeFirst()
                    val distance = current.distanceMap[root]!!
                    visited.add(current)
                    current.neighbors.filter { it !in visited }.forEach { neighbor ->
                        neighbor.distanceMap[root] = distance + 1
                        addLast(neighbor)
                    }
                }
            }
            root.distanceMap.remove(root)
        }
    }

    private var startNode = nodes.find { it.name == "AA" }!!

    private fun maxPath(opened: Set<ValveNode>, node: ValveNode, steps: Int, sum: Int, open: Int): Int = when {
        steps < 0 -> 0
        steps == 0 -> sum
        else -> node.distanceMap.filter { (key, _) -> !opened.contains(key) }
            .map { (nextNode, distance) ->
                maxPath(
                    opened.plus(node),
                    nextNode,
                    steps - (distance + 1),
                    sum + (distance + 1) * open,
                    open + nextNode.flowRate
                )
            }.plus(sum + steps * open)
            .max()
    }

    fun part1() = maxPath(emptySet(), startNode, 30, 0, 0)

    fun part2() = 0 // TODO

}
