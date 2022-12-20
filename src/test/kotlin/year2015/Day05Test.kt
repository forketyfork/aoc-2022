package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day05Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day05_sample1", 1, 0),
                Arguments.of("year2015/Day05_sample2", 1, 0),
                Arguments.of("year2015/Day05_sample3", 0, 0),
                Arguments.of("year2015/Day05_sample4", 0, 0),
                Arguments.of("year2015/Day05_sample5", 0, 0),
                Arguments.of("year2015/Day05_sample6", 0, 1),
                Arguments.of("year2015/Day05_sample7", 0, 1),
                Arguments.of("year2015/Day05_sample8", 0, 0),
                Arguments.of("year2015/Day05_sample9", 0, 0)
            ),
            Arguments.of("year2015/Day05_personal", 238, 69)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day05().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day05().part2(contents)).isEqualTo(part2Answer)
    }
}