fun main() {

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

    fun part1(input: String) = solution(input, 4)

    fun part2(input: String) = solution(input, 14)

    val input = readText("Day06_test")

    part1(input).also { println(it) }.also { check(it == 1140) }
    part2(input).also { println(it) }.also { check(it == 3495) }
}
