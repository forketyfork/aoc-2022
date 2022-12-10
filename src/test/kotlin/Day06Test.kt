import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day06Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            Arguments.of("Day06_sample1", 7, 19),
            Arguments.of("Day06_sample2", 5, 23),
            Arguments.of("Day06_sample3", 6, 23),
            Arguments.of("Day06_sample4", 10, 29),
            Arguments.of("Day06_sample5", 11, 26),
            Arguments.of("Day06_test.txt", 1140, 3495)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day06().part1(contents, Day06::solution)).isEqualTo(part1Answer)
        assertThat(Day06().part2(contents, Day06::solution)).isEqualTo(part2Answer)
        assertThat(Day06().part1(contents, Day06::linearSolution)).isEqualTo(part1Answer)
        assertThat(Day06().part2(contents, Day06::linearSolution)).isEqualTo(part2Answer)
    }
}
