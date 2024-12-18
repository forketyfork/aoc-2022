package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.*
import org.junit.jupiter.params.provider.*

class Day17Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day17_sample1", "4,6,3,5,6,3,5,2,1,0", 0L),
                Arguments.of("year2024/Day17_sample2", "5,7,3,0", 117440L),
            ),
            Arguments.of("year2024/Day17_personal", "6,5,4,7,1,6,0,3,1", 0L)
        )
    }

    @Test
    fun testComputer() {
        // If register C contains 9, the program 2,6 would set register B to 1.
        var computer = Day17.Computer(0, 0, 9, listOf(2, 6))
        computer.run()
        assertThat(computer.a).isEqualTo(0)
        assertThat(computer.b).isEqualTo(1)
        assertThat(computer.c).isEqualTo(9)

        // If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
        computer = Day17.Computer(10, 0, 0, listOf(5, 0, 5, 1, 5, 4))
        var output = computer.run()
        assertThat(output).isEqualTo(listOf(0, 1, 2))

        // If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
        computer = Day17.Computer(2024, 0, 0, listOf(0, 1, 5, 4, 3, 0))
        output = computer.run()
        assertThat(output).isEqualTo(listOf(4,2,5,6,7,7,7,7,3,1,0))
        assertThat(computer.a).isEqualTo(0)

        // If register B contains 29, the program 1,7 would set register B to 26.
        computer = Day17.Computer(0, 29, 0, listOf(1, 7))
        output = computer.run()
        assertThat(computer.b).isEqualTo(26)

        // If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
        computer = Day17.Computer(0, 2024, 43690, listOf(4, 0))
        output = computer.run()
        assertThat(computer.b).isEqualTo(44354)
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: String, part2Answer: Long) {
        val contents = readTestFile(file)
        assertThat(Day17().part1(contents)).isEqualTo(part1Answer)
        if (!file.endsWith("sample1")) {
            assertThat(Day17().part2(contents)).isEqualTo(part2Answer)
        }
    }

}
