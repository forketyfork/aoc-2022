package year2023

class Day02 {

    companion object {
        @JvmStatic
        val maxColorNumbers = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
    }

    private fun parseLine(line: String): Pair<Int, List<Pair<String, Int>>> {
        val (lineStart, lineEnd) = line.split(':')

        val gameNumber = lineStart.removePrefix("Game ").toInt()

        val colorList = lineEnd.split(';').flatMap { iteration ->
            val colors = iteration.split(',').map { it.trim() }
            colors.map { it.split(' ') }.map { it[1] to it[0].toInt() }
        }

        return gameNumber to colorList
    }

    fun part1(input: String): Int {
        return input.lines()
            .filter { it.isNotBlank() }.sumOf { line ->
                val (gameNumber, colorList) = parseLine(line)
                if (colorList.all { pair -> maxColorNumbers[pair.first]!! >= pair.second }) {
                    gameNumber
                } else {
                    0
                }
            }
    }

    fun part2(input: String): Int {
        return input.lines()
            .filter { it.isNotBlank() }.sumOf { line ->
                buildMap {
                    parseLine(line).second.forEach { pair ->
                        put(pair.first, maxOf(getOrDefault(pair.first, 0), pair.second))
                    }
                }.values.reduce(Int::times)
            }
    }

}
