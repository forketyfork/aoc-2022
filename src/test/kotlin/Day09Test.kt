import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day09Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            Arguments.of("Day09_sample1", 13, 1),
            Arguments.of("Day09_sample2", 88, 36),
            Arguments.of("Day09_test.txt", 6406, 2643)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day09().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day09().part2(contents)).isEqualTo(part2Answer)
    }
}
