class Day10 {

    fun solution(input: String): Pair<Int, String> {
        var result = 0
        val printout = buildString {
            var cycle = 0
            var x = 1
            fun bumpCycle() {
                append(if (cycle % 40 in (x - 1..x + 1)) '█' else ' ')
                when (++cycle % 40) {
                    20 -> result += x * cycle
                    0 -> appendLine()
                }
            }
            input.lines().forEach { line ->
                when (line.substringBefore(" ")) {
                    "noop" -> bumpCycle()
                    "addx" -> {
                        repeat(2) { bumpCycle() }
                        x += line.substringAfter(" ").toInt()
                    }
                }
            }
        }
        return result to printout
    }

}
