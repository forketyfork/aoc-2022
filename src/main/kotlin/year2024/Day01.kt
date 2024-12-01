package year2024

import kotlin.math.*

class Day01 {

    companion object {
        val SPACES = "\\s+".toRegex()
    }

    fun part1(input: String): Long {
        val (list1, list2) = toLists(input)
        return list1.sorted().zip(list2.sorted()).sumOf { abs(it.first - it.second) }
    }

    fun part2(input: String): Long {
        val (list1, list2) = toLists(input)
        val map = list2.groupBy { it }.mapValues { it.value.count() }
        return list1.sumOf { it * (map[it] ?: 0) }
    }

    private fun toLists(input: String): Pair<List<Long>, List<Long>> {
        val pairs = input.lines().map {
            it.split(SPACES).map { it.toLong() }
        }
        val list1 = pairs.map { it[0] }
        val list2 = pairs.map { it[1] }
        return Pair(list1, list2)
    }

}
