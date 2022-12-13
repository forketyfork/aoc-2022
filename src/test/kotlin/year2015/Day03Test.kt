package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day03Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day03_sample1", 2, 2),
                Arguments.of("year2015/Day03_sample2", 4, 3),
                Arguments.of("year2015/Day03_sample3", 2, 11),
                Arguments.of("year2015/Day03_sample4", 2, 3),
            ),
            Arguments.of("year2015/Day03_personal", 2565, 2639)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day03().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day03().part2(contents)).isEqualTo(part2Answer)
    }
}