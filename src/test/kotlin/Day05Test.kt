import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day05Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            Arguments.of("Day05_sample", "CMZ", "MCD"),
            Arguments.of("Day05_test.txt", "CNSZFDVLJ", "QNDWLMGNS")
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: String, part2Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day05(contents).part1()).isEqualTo(part1Answer)
        assertThat(Day05(contents).part2()).isEqualTo(part2Answer)
    }
}
