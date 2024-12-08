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
                Arguments.of("year2024/Day08_sample1", 14, 34),
                Arguments.of("year2024/Day08_sample2", 2, 5),
                Arguments.of("year2024/Day08_sample3", 4, 8),
                Arguments.of("year2024/Day08_sample4", 3, 9),
            ),
            Arguments.of("year2024/Day08_personal", 351, 1259)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day08().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day08().part2(contents)).isEqualTo(part2Answer)
    }
}
