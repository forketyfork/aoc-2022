package year2015

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day04Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2015/Day04_sample1", 609043, 6742839),
                Arguments.of("year2015/Day04_sample2", 1048970, 5714438)
            ),
            Arguments.of("year2015/Day04_personal", 117946, 3938038)
        )
    }

    @ParameterizedTest(name = "File {0}, part1Answer = {1}, part2Answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day04().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day04().part2(contents)).isEqualTo(part2Answer)
    }
}