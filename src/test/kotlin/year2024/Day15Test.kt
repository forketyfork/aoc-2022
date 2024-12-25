package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day15Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day15_sample1", 2028L, 0L),
                Arguments.of("year2024/Day15_sample2", 10092L, 0L),
            ),
            Arguments.of("year2024/Day15_personal", 1463715L, 0L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day15().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day15().part2(contents)).isEqualTo(part2Answer)
    }
}
