import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day08Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            Arguments.of("Day08_sample", 21, 8),
            Arguments.of("Day08_test.txt", 1798, 259308)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day08().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day08().part2(contents)).isEqualTo(part2Answer)
    }
}
