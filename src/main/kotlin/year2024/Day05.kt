package year2024

class Day05 {

    fun part1(input: String) = solve(input, true)

    fun part2(input: String) = solve(input, false)

    fun solve(input: String, correct: Boolean): Long {
        val (ordering, updates) = input.split("\n\n")
        val order = ordering.lines().toSet()
        return updates.lines()
            .map { it.split(",") }
            .map { it to it.sortedWith { o1, o2 -> if ("$o1|$o2" in order) -1 else if ("$o2|$o1" in order) 1 else 0 } }
            .filter { (it.first == it.second) == correct }
            .sumOf { it.second[it.second.size / 2].toLong() }
    }
}
