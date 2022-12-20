package year2022

import utils.nextIndexOf
import utils.prevIndexOf

class Day20(input: String) {

    private val numbers = input.lines().map(String::toLong)

    inner class Node(val value: Long) {
        var prev: Node? = null
        var next: Node? = null
        fun shift() {
            val shift = value.mod(numbers.size - 1)
            if (shift == 0) {
                return
            }
            val before = nextNode(shift)
            val after = before.next!!
            prev!!.next = next
            next!!.prev = prev
            before.next = this
            after.prev = this
            prev = before
            next = after
        }

        fun nextNode(shift: Int) = generateSequence(this) { it.next }.take(shift + 1).last()
    }

    private fun List<Node>.mix(num: Int) = repeat(num) { forEach(Node::shift) }

    private fun List<Node>.zero() = find { it.value == 0L }!!

    fun part1() = solution(1, 1L)

    fun part2() = solution(10, 811589153L)

    fun solution(mixes: Int, multiplier: Long): Long {
        val nodes = numbers.map { it * multiplier }.map(::Node)
        nodes.forEachIndexed { idx, node ->
            node.next = nodes[idx.nextIndexOf(nodes)]
            node.prev = nodes[idx.prevIndexOf(nodes)]
        }
        nodes.mix(mixes)
        return generateSequence(nodes.zero()) { it.nextNode(1000) }.drop(1). take(3).map(Node::value).sum()
    }
}
