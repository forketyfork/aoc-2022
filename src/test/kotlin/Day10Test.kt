import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Day10Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            arrayOf("Day10_sample", 0, 0),
            arrayOf("Day01_test.txt", 0, 0)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day10().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day10().part2(contents)).isEqualTo(part2Answer)
    }
}
