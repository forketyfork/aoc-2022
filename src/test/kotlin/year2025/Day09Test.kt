package year2025

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day09Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2025/Day09_sample", 50L, 24L),
            ),
            Arguments.of("year2025/Day09_personal", 4763932976L, 0L)
        )
    }

    @ParameterizedTest(name = "File {0}, iterations = {1}, part 1 answer = {2}, part 2 answer = {3}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day09().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day09().part2(contents)).isEqualTo(part2Answer)
    }
}
