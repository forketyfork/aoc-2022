package year2024

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day23Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2024/Day23_sample", 7, "co,de,ka,ta"),
            ),
            Arguments.of("year2024/Day23_personal", 1000, "cf,ct,cv,cz,fi,lq,my,pa,sl,tt,vw,wz,yd")
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = {2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day23().part1(contents)).isEqualTo(part1Answer)
        assertThat(Day23().part2(contents)).isEqualTo(part2Answer)
    }

}
