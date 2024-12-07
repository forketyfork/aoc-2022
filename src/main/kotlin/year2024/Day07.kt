package year2024

class Day07 {

    fun part1(input: String) = solve(input, listOf(Long::times, Long::plus))

    fun part2(input: String) = solve(input, listOf(Long::times, Long::plus, { a, b -> "$a$b".toLong() }))

    fun solve(input: String, ops: List<(Long, Long) -> Long>) = input.lines()
        .map { it.split(":?\\s".toRegex()).map { it.toLong() } }
        .filter { rec(it, ops) }
        .sumOf { it.first() }

    fun rec(values: List<Long>, ops: List<(Long, Long) -> Long>, idx: Int = 1, sum: Long = values[1]): Boolean = when {
        sum > values[0] -> false
        idx == values.lastIndex -> sum == values[0]
        else -> ops.any { rec(values, ops, idx + 1, it(sum, values[idx + 1])) }
    }

}
