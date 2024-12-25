package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day12Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day12_sample1", 140L, 80L),
                Arguments.of("year2024/Day12_sample2", 772L, 436L),
                Arguments.of("year2024/Day12_sample3", 1930L, 1206L),
                Arguments.of("year2024/Day12_sample4", 692L, 236L),
                Arguments.of("year2024/Day12_sample5", 1184L, 368L),
            ),
            Arguments.of("year2024/Day12_personal", 1381056L, 0L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day12().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day12().part2(contents)).isEqualTo(part2Answer)
    }
}
