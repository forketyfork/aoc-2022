package year2024

class Day07 {

    fun part1(input: String) = solve(input, Long::times, Long::plus)

    fun part2(input: String) = solve(input, Long::times, Long::plus, { a, b -> "$a$b".toLong() })

    fun solve(input: String, vararg ops: (Long, Long) -> Long) = input.lines()
        .map { it.split(":?\\s".toRegex()).map { it.toLong() } }
        .filter { rec(it, 1, it[1], *ops) }
        .sumOf { it.first() }

    fun rec(values: List<Long>, idx: Int, sum: Long, vararg ops: (Long, Long) -> Long): Boolean = when {
        sum > values[0] -> false
        idx == values.lastIndex -> sum == values[0]
        else -> ops.any { rec(values, idx + 1, it(sum, values[idx + 1]), *ops) }
    }

}
