package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day13Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day13_sample", 480L, 875318608908L),
            ),
            Arguments.of("year2024/Day13_personal", 30413L, 92827349540204L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day13().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day13().part2(contents)).isEqualTo(part2Answer)
    }
}