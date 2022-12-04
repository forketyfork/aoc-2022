fun main() {

    fun part1(input: List<String>) = input.map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> from1 <= from2 && to1 >= to2 || from1 >= from2 && to1 <= to2 }

    fun part2(input: List<String>) = input.map { it.split(",", "-").map(String::toInt) }
        .count { (from1, to1, from2, to2) -> (from1..to1).intersect((from2..to2)).isNotEmpty() }

    val input = readInput("Day04_test")

    part1(input).also { println(it) }.also { check(it == 528) }
    part2(input).also { println(it) }.also { check(it == 881) }
}
