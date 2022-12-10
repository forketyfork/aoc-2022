class Day06 {
    companion object {
        fun solution(input: String, length: Int) = input.windowed(length)
            .indexOfFirst { it.toSet().size == length } + length

        fun linearSolution(input: String, length: Int): Int {
            var distinctCount = 0
            val charCounts = IntArray(256)
            return 1 + input.indices.first {
                if (it >= length) {
                    when (--charCounts[input[it - length].code]) {
                        0 -> distinctCount--
                        1 -> distinctCount++
                    }
                }
                when (++charCounts[input[it].code]) {
                    1 -> distinctCount++
                    2 -> distinctCount--
                }
                distinctCount == length
            }
        }
    }

    fun part1(input: String, solver: (String, Int) -> Int) = solver(input, 4)

    fun part2(input: String, solver: (String, Int) -> Int) = solver(input, 14)
}
