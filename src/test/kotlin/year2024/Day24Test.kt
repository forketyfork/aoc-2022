package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day24Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day24_sample1", 4L, ""),
                Arguments.of("year2024/Day24_sample2", 2024L, ""),
            ),
            Arguments.of("year2024/Day24_personal", 45121475050728L, "")
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day24().part1(contents)).isEqualTo(part1Answer)
        if (file.endsWith("personal")) {
            assertThat(Day24().part2(contents)).isEqualTo(part2Answer)
        }
    }

}
