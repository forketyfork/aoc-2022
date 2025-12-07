package year2025

import utils.Op

class Day06 {

    companion object {
        val SPACES = Regex("\\s+")
    }

    fun part1(input: String): Long {
        val lines = input.lines()
        val numbers = lines.dropLast(1).map {
            it.trim().split(SPACES).map(String::toLong)
        }
        val ops = lines.last().trim().split(SPACES)
            .map { Op.fromChar(it.first()) }
        return (0..numbers[0].lastIndex).sumOf { colIdx ->
            val op = ops[colIdx]
            numbers.fold(op.identity) { acc, row ->
                op.operation(acc, row[colIdx])
            }
        }
    }

    fun part2(input: String): Long {
        val lines = input.lines()
        val ops = lines.last().trim().split(SPACES)
            .map { Op.fromChar(it.first()) }
        val numberLines = lines.dropLast(1)

        var opIdx = 0
        var result = 0L
        var currOpResult = ops[opIdx].identity

        (0..numberLines[0].lastIndex).forEach { colIdx ->
            val number = numberLines.fold(null as Long?) { acc, row ->
                val c = row[colIdx]
                if (c != ' ') {
                    (acc ?: 0) * 10 + (c - '0')
                } else {
                    acc
                }
            }
            if (number == null) {
                opIdx++
                result += currOpResult
                currOpResult = ops[opIdx].identity
            } else {
                currOpResult = ops[opIdx].operation(currOpResult, number)
            }
        }
        return result + currOpResult
    }

}
