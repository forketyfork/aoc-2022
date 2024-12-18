package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.Point2D

class Day18Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day18_sample", 22, Point2D(6, 1)),
            ),
            Arguments.of("year2024/Day18_personal", 250, Point2D(56, 8))
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Point2D) {
        val contents = readTestFile(file)
        assertThat(Day18().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day18().part2(contents)).isEqualTo(part2Answer)
    }

}
