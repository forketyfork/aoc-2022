package year2017

import Utils.Companion.cases
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Disabled("wip")
class Day03Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of(1, 0, 0),
                Arguments.of(12, 3, 0),
                Arguments.of(23, 2, 0),
                Arguments.of(1024, 31, 0),
            ),
            Arguments.of(368078, 0, 0)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(input: Int, part1Answer: Int, part2Answer: Int?) {
        assertThat(Day03().part1(input)).isEqualTo(part1Answer)
        assertThat(Day03().part2(input)).isEqualTo(part2Answer)
    }
}