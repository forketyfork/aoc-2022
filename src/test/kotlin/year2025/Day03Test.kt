package year2025

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
                Arguments.of("year2025/Day03_sample", 357L, 3121910778619L),
            ),
            Arguments.of("year2025/Day03_personal", 17166L, 169077317650774L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day03().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day03().part2(contents)).isEqualTo(part2Answer)
    }
}
