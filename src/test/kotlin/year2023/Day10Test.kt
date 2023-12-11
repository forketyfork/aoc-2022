package year2023

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day10Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2023/Day10_sample1", 4, 0),
                Arguments.of("year2023/Day10_sample2", 8, 0),
            ),
            Arguments.of("year2023/Day10_personal", 7097, 0)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int?, part2Answer: Int) {
        val contents = readTestFile(file)
        if (part1Answer != null) {
            assertThat(Day10().part1(contents)).isEqualTo(part1Answer)
        }
        assertThat(Day10().part2(contents)).isEqualTo(part2Answer)
    }
}
