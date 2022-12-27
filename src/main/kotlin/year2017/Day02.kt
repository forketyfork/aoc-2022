package year2017

class Day02(contents: String) {

    val input = contents.lines()
        .map {
            it.split(" ", "\t")
                .map(kotlin.String::toInt)
        }

    fun part1() = input.sumOf { it.max() - it.min() }

    fun part2() = input.map { it.sorted() }.sumOf { row ->
        (0..<row.lastIndex).flatMap { i1 ->
            (i1 + 1..row.lastIndex).map { i2 -> row[i1] to row[i2] }
        }.first { it.second % it.first == 0 }
            .run { second / first }
    }

}
