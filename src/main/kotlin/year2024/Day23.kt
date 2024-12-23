package year2024

class Day23 {

    fun part1(input: String): Int {

        val nodes = parseNodes(input)

        return buildSet {
            nodes.forEach { node1 ->
                nodes.forEach { node2 ->
                    nodes.forEach { node3 ->
                        if (node1 != node2 && node2 != node3 &&
                            (node1.name[0] == 't' || node2.name[0] == 't' || node3.name[0] == 't') &&
                            node1 in node2.connected &&
                            node1 in node3.connected &&
                            node2 in node3.connected
                        ) {
                            add(setOf(node1, node2, node3))
                        }
                    }
                }
            }
        }.count()
    }

    fun part2(input: String): String {
        return buildSet {
            findParties(mutableSetOf(), parseNodes(input).toList())
        }.maxBy { it.size }.map { it.name }.sorted().joinToString(",")
    }

    class Node(val name: String, val connected: MutableSet<Node> = mutableSetOf())

    private fun MutableSet<Set<Node>>.findParties(party: Set<Node>, rest: List<Node>) {
        val inviteList = rest.filterTo(ArrayDeque()) { node -> party.all { it.connected.contains(node) } }
        if (inviteList.isEmpty() && party.any { it.name[0] == 't' }) {
            add(party)
        } else {
            while (inviteList.isNotEmpty()) {
                findParties(party + inviteList.removeFirst(), inviteList)
            }
        }
    }

    private fun parseNodes(input: String): Set<Node> = buildMap {
        input.lines().forEach { line ->
            val (name1, name2) = line.split('-')
            val node1 = computeIfAbsent(name1) { Node(name1) }
            val node2 = computeIfAbsent(name2) { Node(name2) }
            node1.connected.add(node2)
            node2.connected.add(node1)
        }
    }.values.toSet()

}
