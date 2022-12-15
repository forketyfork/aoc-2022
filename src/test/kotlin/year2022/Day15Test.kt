package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day15Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day15_sample", 10, 20, 26, 56000011L)
            ),
            Arguments.of("year2022/Day15_personal", 2000000, 4000000, 5144286, 10229191267339L)
        )
    }

    @ParameterizedTest(name = "File {0}, row {1}, maxCoord {2}, part 1 answer = {3}, part 2 answer = {4}")
    @MethodSource("data")
    fun test(file: String, row: Int, maxCoord: Int, part1Answer: Int, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day15(contents).part1(row)).isEqualTo(part1Answer)
        assertThat(Day15(contents).part2(maxCoord)).isEqualTo(part2Answer)
    }
}
