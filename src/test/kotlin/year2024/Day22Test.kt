package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day22Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day22_sample1", 37327623L, 24),
                Arguments.of("year2024/Day22_sample2", 37990510L, 23),
            ),
            Arguments.of("year2024/Day22_personal", 19822877190L, 2277)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day22().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day22().part2(contents)).isEqualTo(part2Answer)
    }

}
