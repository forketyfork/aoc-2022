package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day25Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day25_sample", "2=-1=0"),
            ),
            Arguments.of("year2022/Day25_personal", "2-==10--=-0101==1201")
        )

    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}")
    @MethodSource("data")
    fun test(file: String, part1Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day25(contents).part1()).isEqualTo(part1Answer)
    }

}
