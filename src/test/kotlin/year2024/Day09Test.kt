package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day09Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day09_sample", 1928L, 2858L),
            ),
            Arguments.of("year2024/Day09_personal", 6301895872542L, 6323761685944L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day09().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day09().part2(contents)).isEqualTo(part2Answer)
    }
}
