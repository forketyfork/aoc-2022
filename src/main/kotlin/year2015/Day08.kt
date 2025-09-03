package year2015

class Day08 {

    companion object {
        val HEX = "\\\\x[\\da-f]{2}".toRegex()
    }

    fun part1(input: String) = input.lines().sumOf { line ->
        line.length - line.removePrefix("\"")
            .removeSuffix("\"")
            .replace("\\\\", "*")
            .replace("\\\"", "*")
            .replace(HEX, "*")
            .length
    }

    fun part2(input: String) = input.lines().sumOf { line ->
        line.charLen() - line.length
    }

    fun String.charLen() = 2 + sumOf {
        if (it == '\\' || it == '"') {
            2
        } else {
            1
        }
    }

}
