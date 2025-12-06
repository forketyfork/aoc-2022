package year2025

class Day03 {

    fun part1(input: String) = solve(input, 2)
    fun part2(input: String) = solve(input, 12)

    data class Acc(var idx: Int, var total: Long)

    fun solve(input: String, digits: Int) = input.lines().sumOf { row ->
        ((digits - 1) downTo 0).fold(Acc(0, 0L)) { acc, remaining ->
            val digit = row.drop(acc.idx).dropLast(remaining).max()
            val digitIdx = row.indexOf(digit, acc.idx)
            acc.idx = digitIdx + 1
            acc.total = acc.total * 10 + (digit - '0')
            acc
        }.total
    }
}
