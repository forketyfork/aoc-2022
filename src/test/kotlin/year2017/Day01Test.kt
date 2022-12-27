package year2017

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class Day01Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2017/Day01_sample1", 3, 0),
                Arguments.of("year2017/Day01_sample2", 4, 4),
                Arguments.of("year2017/Day01_sample3", 0, 0),
                Arguments.of("year2017/Day01_sample4", 9, 6),
                Arguments.of("year2017/Day01_sample5", 0, 6),
                Arguments.of("year2017/Day01_sample6", 3, 0),
                Arguments.of("year2017/Day01_sample7", 0, 4),
                Arguments.of("year2017/Day01_sample8", 0, 12),
                Arguments.of("year2017/Day01_sample9", 0, 4),
            ),
            Arguments.of("year2017/Day01_personal", 1177, 1060)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day01().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day01().part2(contents)).isEqualTo(part2Answer)
    }
}