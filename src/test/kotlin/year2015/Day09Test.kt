package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day09Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day09_sample", 605, 982),
            ),
            Arguments.of("year2015/Day09_personal", 251, 898)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day09().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day09().part2(contents)).isEqualTo(part2Answer)
    }
}