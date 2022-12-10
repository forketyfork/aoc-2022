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
                Arguments.of("Day04_sample", 2, 4)
            ),
            Arguments.of("Day04_personal", 528, 881)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day04().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day04().part2(contents)).isEqualTo(part2Answer)
    }
}
