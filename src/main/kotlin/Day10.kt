class Day10 {

    fun solution(input: String): Pair<Int, String> {
        var cycle = 0
        var result = 0
        var x = 1
        val printout = StringBuilder()
        fun bumpCycle() {
            printout.append(if (cycle % 40 in (x - 1..x + 1)) '█' else ' ')
            when (++cycle % 40) {
                20 -> result += x * cycle
                0 -> printout.append('\n')
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
        return result to printout.toString()
    }

}
