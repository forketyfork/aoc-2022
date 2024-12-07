package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day07Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day07_sample", 3749L, 11387L),
            ),
            Arguments.of("year2024/Day07_personal", 4364915411363L, 38322057216320L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day07().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day07().part2(contents)).isEqualTo(part2Answer)
    }
}
