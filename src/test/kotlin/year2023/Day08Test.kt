package year2023

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day08Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2023/Day08_sample1", 2L, 2L),
                Arguments.of("year2023/Day08_sample2", 6L, 6L),
                Arguments.of("year2023/Day08_sample3", null, 6L),
            ),
            Arguments.of("year2023/Day08_personal", 16697L, 10668805667831L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long?, part2Answer: Long) {
        val contents = readTestFile(file)
        if (part1Answer != null) {
            assertThat(Day08().part1(contents)).isEqualTo(part1Answer)
        }
        assertThat(Day08().part2(contents)).isEqualTo(part2Answer)
    }
}
