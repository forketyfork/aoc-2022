package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day20Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day20_sample", 3L, 1623178306L),
            ),
            Arguments.of("year2022/Day20_personal", 1591L, 14579387544492L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day20(contents).part1()).isEqualTo(part1Answer)
        assertThat(Day20(contents).part2()).isEqualTo(part2Answer)
    }
}
