package year2025

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day08Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2025/Day08_sample", 10, 40L, 25272L),
            ),
            Arguments.of("year2025/Day08_personal", 1000, 24360L, 2185817796L)
        )
    }

    @ParameterizedTest(name = "File {0}, iterations = {1}, part 1 answer = {2}, part 2 answer = {3}")
    @MethodSource("data")
    fun test(file: String, part1Iterations: Int, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day08().part1(contents, part1Iterations)).isEqualTo(part1Answer)
        assertThat(Day08().part2(contents)).isEqualTo(part2Answer)
    }
}
