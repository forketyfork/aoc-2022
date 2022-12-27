package year2017

class Day02 {

    fun part1(input: String) = parse(input).sumOf { it.max() - it.min() }

    fun part2(input: String) = parse(input).map { it.sorted() }.sumOf {
        (0..<it.lastIndex).flatMap { i1 ->
            (i1 + 1..it.lastIndex).map { i2 -> it[i1] to it[i2] }
        }.first { (num1, num2) -> num2 % num1 == 0 }
            .let { (num1, num2) -> num2 / num1 }

    }

    private fun parse(input: String) = input.lines()
        .map { it.split(" ", "\t").map(String::toInt) }

}
