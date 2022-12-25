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

        @JvmStatic
        fun toSnafuData() =
            arrayOf(
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(3, "1="),
                Arguments.of(4, "1-"),
                Arguments.of(5, "10"),
                Arguments.of(6, "11"),
                Arguments.of(7, "12"),
                Arguments.of(8, "2="),
                Arguments.of(9, "2-"),
                Arguments.of(10, "20"),
                Arguments.of(11, "21"),
                Arguments.of(15, "1=0"),
                Arguments.of(20, "1-0"),
                Arguments.of(31, "111"),
                Arguments.of(32, "112"),
                Arguments.of(37, "122"),
                Arguments.of(107, "1-12"),
                Arguments.of(198, "2=0="),
                Arguments.of(201, "2=01"),
                Arguments.of(353, "1=-1="),
                Arguments.of(906, "12111"),
                Arguments.of(1257, "20012"),
                Arguments.of(1747, "1=-0-2"),
                Arguments.of(2022, "1=11-2"),
                Arguments.of(4890, "2=-1=0"),
                Arguments.of(12345, "1-0---0"),
                Arguments.of(314159265, "1121-1110-1=0"),
                Arguments.of(12969016535L, "21=1=01=2012120"),
            )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}")
    @MethodSource("data")
    fun test(file: String, part1Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day25(contents).part1()).isEqualTo(part1Answer)
    }

    @ParameterizedTest(name = "Number {0}, SNAFU {1}")
    @MethodSource("toSnafuData")
    fun testToSnafu(number: Long, snafu: String) {
        assertThat(number.toSnafu()).isEqualTo(snafu)
    }

    @ParameterizedTest(name = "Number {0}, SNAFU {1}")
    @MethodSource("toSnafuData")
    fun testFromSnafu(number: Long, snafu: String) {
        assertThat(snafu.fromSnafu()).isEqualTo(number)
    }
}
