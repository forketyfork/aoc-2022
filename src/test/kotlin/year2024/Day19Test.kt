package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day19Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day19_sample", 6, 16L),
            ),
            Arguments.of("year2024/Day19_personal", 287, 571894474468161L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day19().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day19().part2(contents)).isEqualTo(part2Answer)
    }

}
