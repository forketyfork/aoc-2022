package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day21Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day21_sample", 126384L, 0L),
            ),
            Arguments.of("year2024/Day21_personal", 238078L, 293919502998014L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day21().part1(contents)).isEqualTo(part1Answer)
        if (file.endsWith("personal")) {
            assertThat(Day21().part2(contents)).isEqualTo(part2Answer)
        }
    }

}
