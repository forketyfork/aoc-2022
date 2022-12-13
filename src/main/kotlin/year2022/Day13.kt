package year2022

private fun List<*>.compareTo(other: List<*>): Int {

    if (this.isEmpty() && other.isNotEmpty()) {
        return -1
    } else if (this.isNotEmpty() && other.isEmpty()) {
        return 1
    } else if (this.isEmpty()) {
        return 0
    }

    val a = this[0]
    val b = other[0]

    val result = if (a is Int && b is Int) {
        a - b
    } else if (a is Int && b is List<*>) {
        listOf(a).compareTo(b)
    } else if (a is List<*> && b is Int) {
        a.compareTo(listOf(b))
    } else {
        (a as List<*>).compareTo(b as List<*>)
    }

    return if (result == 0) {
        this.drop(1).compareTo(other.drop(1))
    } else {
        result
    }
}

class Day13 {

    fun part1(input: String) = input.split("\n\n")
        .mapIndexed(::Pair)
        .filter { pair ->
            val (array1, array2) = pair.second.split("\n").map(::parse)
            array1.compareTo(array2) <= 0
        }.sumOf { it.first + 1 }

    private fun parse(line: String): List<*> {

        val stack = ArrayDeque<MutableList<Any>>()
        var current = mutableListOf<Any>()
        var number = -1

        fun flushNumber() {
            if (number != -1) {
                current.add(number)
                number = -1
            }
        }

        line.forEach { char ->
            when (char) {
                '[' -> {
                    stack.addLast(current)
                    current = mutableListOf()
                    stack.last().add(current)
                }

                ']' -> {
                    flushNumber()
                    current = stack.removeLast()
                }

                ',' -> flushNumber()

                else -> number = number.coerceAtLeast(0) * 10 + (char - '0')
            }
        }
        return current[0] as List<*>
    }

    fun part2(input: String): Int {

        val marker1 = listOf(listOf(2))
        val marker2 = listOf(listOf(6))

        val lines = input.lines().asSequence()
            .filter { it.isNotEmpty() }
            .map(::parse)
            .plus(marker1)
            .plus(marker2)
            .sortedWith(List<*>::compareTo)
            .toList()

        return (1 + lines.indexOfFirst { it.compareTo(marker1) == 0 }) *
                (1 + lines.indexOfFirst { it.compareTo(marker2) == 0 })
    }

}