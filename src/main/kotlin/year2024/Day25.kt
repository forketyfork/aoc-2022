package year2024

import utils.CharGrid

class Day25 {

    fun part1(input: String): Int {

        val (keys, locks) = input.split("\n\n")
            .map(::CharGrid)
            .partition { it.row(0) == ".".repeat(5) }

        val keyPatterns = keys.map { key -> key.cols().map { it.count { it == '#' } } }
        val lockPatterns = locks.map { lock -> lock.cols().map { it.count { it == '#' } } }

        return keyPatterns.flatMap { key ->
            lockPatterns.map { lock ->
                key.zip(lock).map { it.first + it.second }.all { it <= 7 }
            }
        }.count { it }
    }

}
