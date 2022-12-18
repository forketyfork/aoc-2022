package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day18Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day18_sample1", 10, 10),
                Arguments.of("year2022/Day18_sample2", 64, 58)
            ),
            Arguments.of("year2022/Day18_personal", 4282, 2452)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day18(contents).part1()).isEqualTo(part1Answer)
        assertThat(Day18(contents).part2()).isEqualTo(part2Answer)
    }
}
