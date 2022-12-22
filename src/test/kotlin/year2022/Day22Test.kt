package year2022

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
                Arguments.of("year2022/Day22_sample", 4, 6032L, 5031L),
            ),
            Arguments.of("year2022/Day22_personal", 50, 13566L, 11451L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, cubeSide: Int, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day22(contents).part1()).isEqualTo(part1Answer)
        assertThat(Day22(contents).part2(cubeSide)).isEqualTo(part2Answer)
    }
}
