package year2015

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
                Arguments.of("year2015/Day02_sample1", 58, 34),
                Arguments.of("year2015/Day02_sample2", 43, 14),
            ),
            Arguments.of("year2015/Day02_personal", 1598415, 3812909)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day02().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day02().part2(contents)).isEqualTo(part2Answer)
    }
}