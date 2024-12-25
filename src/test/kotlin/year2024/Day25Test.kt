package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day25Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day25_sample", 3),
            ),
            Arguments.of("year2024/Day25_personal", 3021)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day25().part1(contents)).isEqualTo(part1Answer)
    }

}
