package year2025

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
                Arguments.of("year2025/Day05_sample", 3, 14L),
            ),
            Arguments.of("year2025/Day05_personal", 517, 336173027056994L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day05().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day05().part2(contents)).isEqualTo(part2Answer)
    }
}
