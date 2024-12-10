package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day10Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day10_sample1", 1, 16),
                Arguments.of("year2024/Day10_sample2", 2, 2),
                Arguments.of("year2024/Day10_sample3", 4, 13),
                Arguments.of("year2024/Day10_sample4", 3, 3),
                Arguments.of("year2024/Day10_sample5", 36, 81),
                Arguments.of("year2024/Day10_sample6", 1, 3),
                Arguments.of("year2024/Day10_sample7", 4, 13),
                Arguments.of("year2024/Day10_sample8", 2, 227),
            ),
            Arguments.of("year2024/Day10_personal", 611, 1380)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day10().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day10().part2(contents)).isEqualTo(part2Answer)
    }
}
