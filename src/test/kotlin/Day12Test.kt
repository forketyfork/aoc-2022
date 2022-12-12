import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day12Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("Day12_sample", 31, 29)
            ),
            Arguments.of("Day12_personal", 391, 386)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day12().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day12().part2(contents)).isEqualTo(part2Answer)
    }
}
