package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day08Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day08_sample1", 14L, 34L),
                Arguments.of("year2024/Day08_sample2", 2L, 5L),
                Arguments.of("year2024/Day08_sample3", 4L, 8L),
                Arguments.of("year2024/Day08_sample4", 3L, 9L),
            ),
            Arguments.of("year2024/Day08_personal", 351L, 1259L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day08().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day08().part2(contents)).isEqualTo(part2Answer)
    }
}
