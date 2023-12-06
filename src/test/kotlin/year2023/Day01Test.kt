package year2023

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
                Arguments.of("year2023/Day01_sample1", 142, 142),
                Arguments.of("year2023/Day01_sample2", null, 281)
            ),
            Arguments.of("year2023/Day01_personal", 55477, 54431)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int?, part2Answer: Int) {
        val contents = readTestFile(file)
        if (part1Answer != null) {
            assertThat(Day01().part1(contents)).isEqualTo(part1Answer)
        }
        assertThat(Day01().part2(contents)).isEqualTo(part2Answer)
    }
}
