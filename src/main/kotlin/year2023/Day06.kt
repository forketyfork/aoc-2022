package year2023

class Day06 {

    data class RaceResults(val time: Long, val distance: Long)

    fun part1(input: String): Long {
        return solve(parseInput1(input))
    }

    fun part2(input: String): Long {
        return solve(parseInput2(input))
    }

    fun solve(raceResults: List<RaceResults>): Long {
        return raceResults.map { result ->
            (0..result.time).count { timeToHold ->
                timeToHold * (result.time - timeToHold) > result.distance
            }.toLong()
        }.reduce(Long::times)
    }

    fun parseInput1(input: String): List<RaceResults> {
        val (times, distances) = input.lines()
            .map {
                it.substringAfter(':')
                    .split(' ')
                    .filter { it.isNotBlank() }
                    .map { it.toLong() }
            }
        return times.zip(distances) { time, distance -> RaceResults(time, distance) }
    }

    fun parseInput2(input: String): List<RaceResults> {
        val (time, distance) = input.lines()
            .map {
                it.substringAfter(':')
                    .split(' ')
                    .joinToString("")
                    .toLong()
            }
        return listOf(RaceResults(time, distance))
    }
}
