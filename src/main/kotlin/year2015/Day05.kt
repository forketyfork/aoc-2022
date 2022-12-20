package year2015

class Day05 {

    private val vowels = "aeiou".toSet()
    private val badStrings = listOf("ab", "cd", "pq", "xy")

    fun part1(input: String) = input.lines().count {
        it.count { char -> char in vowels } >= 3
                && it.windowed(2).any { pair -> pair[0] == pair[1] }
                && badStrings.none(it::contains)
    }

    fun part2(input: String) = input.lines().count {
        it.windowed(3).any { triple -> triple[0] == triple[2] }
                && with(mutableMapOf<String, Int>()) {
            it.windowed(2).withIndex().any { (idx, pair) ->
                get(pair)?.let { firstIdx ->
                    idx - firstIdx > 1
                } ?: false.also { put(pair, idx) }
            }
        }
    }

}
