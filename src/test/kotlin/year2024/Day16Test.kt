package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class Day16Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day16_sample1", 7036L, 45L),
                Arguments.of("year2024/Day16_sample2", 11048L, 64L),
            ),
            Arguments.of("year2024/Day16_personal", 109516L, 568L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day16().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day16().part2(contents)).isEqualTo(part2Answer)
    }

}
