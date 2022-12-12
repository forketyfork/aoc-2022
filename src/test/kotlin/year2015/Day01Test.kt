package year2015

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
                Arguments.of("year2015/Day01_sample1", 0, -1),
                Arguments.of("year2015/Day01_sample2", 0, -1),
                Arguments.of("year2015/Day01_sample3", 3, -1),
                Arguments.of("year2015/Day01_sample4", 3, -1),
                Arguments.of("year2015/Day01_sample5", 3, 1),
                Arguments.of("year2015/Day01_sample6", -1, 3),
                Arguments.of("year2015/Day01_sample7", -1, 1),
                Arguments.of("year2015/Day01_sample8", -3, 1),
                Arguments.of("year2015/Day01_sample9", -3, 1),
                Arguments.of("year2015/Day01_sample10", -1, 1),
                Arguments.of("year2015/Day01_sample11", -1, 5),
            ),
            Arguments.of("year2015/Day01_personal", 74, 1795)
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