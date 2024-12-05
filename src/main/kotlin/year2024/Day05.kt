package year2024

class Day05 {

    fun part1(input: String) = solve(input, true)

    fun part2(input: String) = solve(input, false)

    fun solve(input: String, correct: Boolean): Long {
        return partition(input)
            .filter { (it.first == it.second) == correct }
            .sumOf { it.second[it.second.size / 2].toLong() }
    }

    fun partition(input: String): List<Pair<List<String>, List<String>>> {
        val (ordering, updates) = input.split("\n\n")
        val orderingSet = ordering.lines().toSet()
        return updates.lines().map { it.split(",") }.map {
            it to it.sortedWith { o1, o2 ->
                if (orderingSet.contains("$o1|$o2")) {
                    -1
                } else if (orderingSet.contains("$o2|$o1")) {
                    1
                } else {
                    0
                }
            }
        }
    }

}
