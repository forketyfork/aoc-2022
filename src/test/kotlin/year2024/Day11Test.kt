package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day11Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day11_sample", 55312L, 65601038650482L),
            ),
            Arguments.of("year2024/Day11_personal", 220722L, 261952051690787L)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Long, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day11().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day11().part2(contents)).isEqualTo(part2Answer)
    }
}
