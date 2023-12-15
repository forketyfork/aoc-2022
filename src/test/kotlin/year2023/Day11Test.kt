package year2023

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day11Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2023/Day11_sample", 374L, 82000210L),
            ),
            Arguments.of("year2023/Day11_personal", 9724940L, 569052586852L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day11().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day11().part2(contents)).isEqualTo(part2Answer)
    }
}
