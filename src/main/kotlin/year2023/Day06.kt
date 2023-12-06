package year2023

class Day06 {

    data class RaceResult(val time: Long, val distance: Long)

    fun part1(input: String) = input.parse { split(' ').mapNotNull { it.toLongOrNull() } }.solve()

    fun part2(input: String) = input.parse { listOf(replace(" ", "").toLong()) }.solve()

    fun String.parse(toListOfLongs: String.() -> List<Long>): List<RaceResult> = lines().map {
        it.substringAfter(':').toListOfLongs()
    }.let { it[0].zip(it[1], ::RaceResult) }

    fun List<RaceResult>.solve() = fold(1) { acc, result ->
        acc * (0..result.time).count { timeToHold ->
            timeToHold * (result.time - timeToHold) > result.distance
        }
    }

}
