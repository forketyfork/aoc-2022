package year2023

class Day02 {

    val maxColorNumbers = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun parseLine(line: String): Pair<Int, List<Pair<String, Int>>> {
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
            .filter { it.isNotBlank() }
            .map { line ->
                val (gameNumber, colorList) = parseLine(line)
                if (colorList.all { pair -> maxColorNumbers[pair.first]!! >= pair.second }) {
                    gameNumber
                } else {
                    0
                }
            }.sum()
    }

    fun part2(input: String): Int {
        return input.lines()
            .filter { it.isNotBlank() }
            .map { line ->

                val colorNumbers = mutableMapOf<String, Int>()

                val (_, colorList) = parseLine(line)

                colorList.forEach { pair ->
                    colorNumbers[pair.first] = maxOf(colorNumbers.getOrDefault(pair.first, 0), pair.second)
                }

                colorNumbers.values.reduce(Int::times)
            }.sum()
    }

}
