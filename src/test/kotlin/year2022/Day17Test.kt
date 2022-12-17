package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day17Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day17_sample", 3068, 0)
            ),
            Arguments.of("year2022/Day17_personal", 0, 0) // 3007 too low
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day17().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day17().part2(contents)).isEqualTo(part2Answer)
    }
}
