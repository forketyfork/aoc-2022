import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class Day02Test {

    companion object {
        @JvmStatic
        fun data() = arrayOf(
            arrayOf("Day02_sample", 15, 12),
            arrayOf("Day02_test.txt", 13484, 13433)
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: Int) {
        val contents = readTestFile(file)
        assertThat(Day02().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day02().part2(contents)).isEqualTo(part2Answer)
    }
}
