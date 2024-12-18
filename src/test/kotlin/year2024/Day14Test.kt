package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day14Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day14_sample", 12L, 0L),
            ),
            Arguments.of("year2024/Day14_personal", 221142636L, 7916L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day14().part1(contents)).isEqualTo(part1Answer)
        if (file.endsWith("personal")) {
            assertThat(Day14().part2(contents)).isEqualTo(part2Answer)
        }
    }
}
