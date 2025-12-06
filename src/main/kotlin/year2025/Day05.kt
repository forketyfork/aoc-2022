package year2025

import kotlin.math.max

class Day05 {

    fun part1(input: String): Int {
        val (ranges, numbers) = parse(input)
        return numbers.count { number ->
            ranges.any { range ->
                range.contains(number)
            }
        }
    }

    fun part2(input: String): Long {
        val (ranges, _) = parse(input)
        val sortedRanges = ranges.sortedBy { it.first }.toMutableList()
        var i = 0
        while (i < sortedRanges.lastIndex) {
            if (sortedRanges[i].last >= sortedRanges[i + 1].first) {
                sortedRanges[i] = sortedRanges[i].first..max(sortedRanges[i].last, sortedRanges[i + 1].last)
                sortedRanges.removeAt(i + 1)
            } else {
                i++
            }
        }
        return sortedRanges.sumOf { it.last - it.first + 1 }
    }

    private fun parse(input: String): Pair<List<LongRange>, List<Long>> {
        val (rangesStr, numbersStr) = input.split("\n\n")
        val ranges = rangesStr.lines().map {
            val (from, to) = it.split("-").map(String::toLong)
            from..to
        }
        val numbers = numbersStr.lines().map(String::toLong)
        return ranges to numbers
    }

}
