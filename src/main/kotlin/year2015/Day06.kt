package year2015

import utils.Point2D
import kotlin.math.max

class Day06 {

    fun part1(input: String) = solution(
        input, mapOf(
            "turn on " to { _: Int -> 1 },
            "turn off " to { _: Int -> 0 },
            "toggle " to { i: Int -> 1 - i },
        )
    )

    fun part2(input: String) = solution(
        input, mapOf(
            "turn on " to { i: Int -> i + 1 },
            "turn off " to { i: Int -> max(0, i - 1) },
            "toggle " to { i: Int -> i + 2 },
        )
    )

    fun solution(input: String, rules: Map<String, (Int) -> Int>) =
        MutableList(1000) { MutableList(1000) { 0 } }.apply {
            input.lines().map { line ->
                rules.entries.find { (key, _) -> line.startsWith(key) }
                    ?.let { (key, value) ->
                        val (from, to) = line.substringAfter(key).split(" through ").map(Point2D::parse)
                        for (x in from.x..to.x) {
                            for (y in from.y..to.y) {
                                this[x][y] = value(this[x][y])
                            }
                        }

                    }
            }
        }.flatten().sum()

}
