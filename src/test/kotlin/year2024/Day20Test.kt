package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day20Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day20_sample", 2, 44, 0L),
            ),
            Arguments.of("year2024/Day20_personal", 100, 1367, 0L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1MinSavedTime: Int, part1Answer: Int, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day20().part1(contents, part1MinSavedTime)).isEqualTo(part1Answer)
        assertThat(Day20().part2(contents)).isEqualTo(part2Answer)
    }

}
