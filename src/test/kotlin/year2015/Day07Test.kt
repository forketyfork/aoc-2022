package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day07Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day07_sample", "d", 72L, 0L),
                Arguments.of("year2015/Day07_sample", "e", 507L, 0L),
                Arguments.of("year2015/Day07_sample", "f", 492L, 0L),
                Arguments.of("year2015/Day07_sample", "g", 114L, 0L),
                Arguments.of("year2015/Day07_sample", "h", 65412L, 0L),
                Arguments.of("year2015/Day07_sample", "i", 65079L, 0L),
                Arguments.of("year2015/Day07_sample", "x", 123L, 0L),
                Arguments.of("year2015/Day07_sample", "y", 456L, 0L),
            ),
            Arguments.of("year2015/Day07_personal", "a", 46065L, 14134L)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, root: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day07().part1(contents, root)).isEqualTo(part1Answer)
        if (file.endsWith("personal")) {
            assertThat(Day07().part2(contents)).isEqualTo(part2Answer)
        }
    }
}