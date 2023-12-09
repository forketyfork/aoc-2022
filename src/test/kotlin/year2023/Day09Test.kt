package year2023

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
                Arguments.of("year2023/Day09_sample", 114L, 2L),
            ),
            Arguments.of("year2023/Day09_personal", 2008960228L, 1097L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long?, part2Answer: Long) {
        val contents = readTestFile(file)
        if (part1Answer != null) {
            assertThat(Day09().part1(contents)).isEqualTo(part1Answer)
        }
        assertThat(Day09().part2(contents)).isEqualTo(part2Answer)
    }
}
