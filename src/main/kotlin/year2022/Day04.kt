package year2022

import kotlin.math.sqrt
import kotlin.math.pow


class Day04 {

    fun part1(input: String) = input.lines().map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> from1 <= from2 && to1 >= to2 || from1 >= from2 && to1 <= to2 }

    fun part2(input: String) = input.lines().map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> (from1..to1).intersect((from2..to2)).isNotEmpty() }
}



fun Int.isSquare() = sqrt(toDouble()).pow(2).toInt() == this

class Solution {
    fun numSquares(n: Int): Int {
        if (n.isSquare()) {
            return 1
        }
        return (1..sqrt(n.toDouble()).toInt()).minOf { base ->
            1 + numSquares(n - base * base) }
    }
}