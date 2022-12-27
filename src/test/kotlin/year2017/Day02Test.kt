package year2017

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class Day02Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2017/Day02_sample1", 18, null),
                Arguments.of("year2017/Day02_sample2", 18, 9),
            ),
            Arguments.of("year2017/Day02_personal", 39126, 258)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int?) {
        val contents = readTestFile(file)
        assertThat(Day02(contents).part1()).isEqualTo(part1Answer)
        if (part2Answer != null) {
            assertThat(Day02(contents).part2()).isEqualTo(part2Answer)
        }
    }
}