package year2024

class Day07 {

    fun part1(input: String) = solve(input, Op.entries.minus(Op.CONCAT))

    fun part2(input: String) = solve(input, Op.entries)

    fun solve(input: String, ops: List<Op>) = input.lines()
        .map { it.split(":?\\s".toRegex()).map { it.toLong() } }
        .filter { rec(it, ops) }
        .sumOf { it.first() }


    fun rec(values: List<Long>, ops: List<Op>, idx: Int = 1, sum: Long? = values[1]): Boolean = when {
        sum == null || sum > values[0] -> false
        idx == values.lastIndex -> sum == values[0]
        else -> ops.any { op -> rec(values, ops, idx + 1, op.fn(sum, values[idx + 1])) }
    }

    enum class Op(val fn: (Long, Long) -> Long?) {
        MUL(Long::times),
        SUM(Long::plus),
        CONCAT({ a, b -> "$a$b".toLongOrNull() }),
    }

}
