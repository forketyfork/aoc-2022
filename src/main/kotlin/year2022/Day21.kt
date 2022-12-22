package year2022

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.Direction
import utils.Direction.LEFT
import utils.Direction.RIGHT

class Day21 {

    enum class Op(
        val char: Char,
        val operation: (Long, Long) -> Long,
        val leftReverse: (Long, Long) -> Long,
        val rightReverse: (Long, Long) -> Long
    ) {
        ADD('+', { a, b -> a + b }, { a, b -> a - b }, { a, b -> a - b }),
        SUB('-', { a, b -> a - b }, { a, b -> a + b }, { a, b -> b - a }),
        DIV('/', { a, b -> a / b }, { a, b -> a * b }, { a, b -> b / a }),
        MUL('*', { a, b -> a * b }, { a, b -> a / b }, { a, b -> a / b });

        companion object {
            fun fromChar(char: Char): Op = values().find { it.char == char }!!
        }
    }

    class Node(val name: String) {

        var actualValue: Long? = null
        var op: Op? = null
        var left: Node? = null
        var right: Node? = null
        private var humnPosition: Direction? = null
        fun value(): Long = actualValue ?: op!!.operation(left!!.value(), right!!.value())

        fun calculateHumnPosition(): Boolean {
            if (name == "humn") {
                return true
            }
            if (actualValue != null) {
                return false
            }
            if (left!!.calculateHumnPosition()) {
                humnPosition = LEFT
                return true
            }
            if (right!!.calculateHumnPosition()) {
                humnPosition = RIGHT
                return true
            }
            return false
        }

        fun calculateHumnValue(expectedNodeValue: Long): Long = if (name == "humn") {
            expectedNodeValue
        } else if (humnPosition == LEFT) {
            left!!.calculateHumnValue(op!!.leftReverse(expectedNodeValue, right!!.value()))
        } else {
            right!!.calculateHumnValue(op!!.rightReverse(expectedNodeValue, left!!.value()))
        }

    }

    private fun buildGraph(input: String) = buildMap<String, Node> {
        input.lines().forEach { line ->
            val name = line.substringBefore(':')
            val node = computeIfAbsent(name, ::Node)
            val value = line.substringAfter(": ")
            node.actualValue = if (value[0].isDigit()) value.toLong() else null.also {
                node.left = computeIfAbsent(value.substring(0..3), ::Node)
                node.right = computeIfAbsent(value.substring(7..10), ::Node)
                node.op = Op.fromChar(value[5])
            }
        }
    }["root"]!!.also { it.calculateHumnPosition() }

    fun part1(input: String) = buildGraph(input).value()

    fun part1Coroutines(input: String) = runBlocking {
        buildMap<String, Channel<Long>> {
            input.lines().forEach { line ->
                val channel = computeIfAbsent(line.substringBefore(':')) { _ -> Channel() }
                launch {
                    val source = line.substringAfter(": ")
                    channel.send(
                        if (source[0].isDigit()) {
                            source.toLong()
                        } else {
                            mapOf<Char, (Long, Long) -> Long>(
                                '+' to Long::plus,
                                '-' to Long::minus,
                                '/' to Long::div,
                                '*' to Long::times
                            )[source[5]]!!.invoke(
                                computeIfAbsent(source.substring(0..3)) { _ -> Channel() }.receive(),
                                computeIfAbsent(source.substring(7)) { _ -> Channel() }.receive()
                            )
                        }
                    )
                }
            }
        }["root"]!!.receive()
    }


    fun part2(input: String) = buildGraph(input).also { it.op = Op.SUB }.calculateHumnValue(0L)

}
