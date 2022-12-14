package year2022

import Utils.Companion.cases
import Utils.Companion.readTestFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Day10Test {

    companion object {
        @JvmStatic
        fun data() = cases(
            arrayOf(
                Arguments.of("year2022/Day10_sample1", 0, "█████"),
                Arguments.of(
                    "year2022/Day10_sample2", 13140, """
                    ██  ██  ██  ██  ██  ██  ██  ██  ██  ██  
                    ███   ███   ███   ███   ███   ███   ███ 
                    ████    ████    ████    ████    ████    
                    █████     █████     █████     █████     
                    ██████      ██████      ██████      ████
                    ███████       ███████       ███████     
                    
                """.trimIndent()
                )
            ),
            Arguments.of(
                "year2022/Day10_personal", 12520, """
                ████ █  █ ███  ████ ███    ██  ██  █    
                █    █  █ █  █    █ █  █    █ █  █ █    
                ███  ████ █  █   █  █  █    █ █    █    
                █    █  █ ███   █   ███     █ █ ██ █    
                █    █  █ █    █    █    █  █ █  █ █    
                ████ █  █ █    ████ █     ██   ███ ████ 
                
            """.trimIndent()
            )
        )
    }

    @ParameterizedTest(name = "File {0}, part 1 answer = {1}, part 2 answer = \n{2}")
    @MethodSource("data")
    fun test(file: String, part1Answer: Int, part2Answer: String) {
        val contents = readTestFile(file)
        assertThat(Day10().solution(contents)).isEqualTo(Pair(part1Answer, part2Answer))
    }
}
