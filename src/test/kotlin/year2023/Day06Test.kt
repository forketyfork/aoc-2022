package year2023

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
                Arguments.of("year2023/Day06_sample", 288L, 71503L),
            ),
            Arguments.of("year2023/Day06_personal", 505494L, 23632299L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day06().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day06().part2(contents)).isEqualTo(part2Answer)
    }
}
