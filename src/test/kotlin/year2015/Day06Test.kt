package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day06Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day06_sample", 998996, 1001996)
            ),
            Arguments.of("year2015/Day06_personal", 400410, 15343601)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day06().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day06().part2(contents)).isEqualTo(part2Answer)
    }
}