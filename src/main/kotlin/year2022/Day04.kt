package year2022


class Day04 {

    fun part1(input: String) = input.lines().map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> from1 <= from2 && to1 >= to2 || from1 >= from2 && to1 <= to2 }

    fun part2(input: String) = input.lines().map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> (from1..to1).intersect((from2..to2)).isNotEmpty() }
}
