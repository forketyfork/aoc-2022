package year2023

class Day09 {

    fun part1(input: String) = solve(parseInput(input, false))
    fun part2(input: String) = solve(parseInput(input, true))

    fun parseInput(input: String, reverse: Boolean) = input.lines()
        .map { line ->
            line.split(' ').map { num -> num.toLong() }
                .let { if (reverse) it.reversed() else it }
                .toMutableList()
        }.toMutableList()

    fun calculateNextRow(row: List<Long>): MutableList<Long> {
        return buildList {
            row.indices.drop(1).forEach { idx ->
                add(row[idx] - row[idx - 1])
            }
        }.toMutableList()
    }

    fun solve(series: MutableList<MutableList<Long>>): Long {
        return series.map { line ->
            val rowList = mutableListOf<MutableList<Long>>()
            var row = line
            rowList.add(row)
            while (row.hasNonZeroes()) {
                val nextRow = calculateNextRow(row)
                rowList.add(nextRow)
                row = nextRow
            }
            rowList.indices.drop(1).reversed().forEach { idx ->
                rowList[idx - 1].add(rowList[idx - 1].last() + rowList[idx].last())
            }
            rowList[0].last()

        }.sum()
    }

    fun List<Long>.hasNonZeroes() = any { it != 0L }

}
