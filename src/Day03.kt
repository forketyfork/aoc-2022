fun main() {

    fun scorer(ch: Char) = 1 + when (ch) {
        in ('a'..'z') -> ch - 'a'
        else -> 26 + (ch - 'A')
    }

    fun part1(input: List<String>) =
            input.asSequence()
                    .map { it.chunked(it.length / 2).map(String::toSet) }
                    .map { (r1, r2) -> r1.intersect(r2) }
                    .map(Set<Char>::first)
                    .sumOf(::scorer)

    fun part2(input: List<String>) =
            input.asSequence()
                    .map(String::toSet)
                    .chunked(3)
                    .map { (r1, r2, r3) -> r1.intersect(r2).intersect(r3) }
                    .map(Set<Char>::first)
                    .sumOf(::scorer)

    val input = readInput("Day03_test")

    part1(input).also { println(it) }.also { check(it == 7990) }
    part2(input).also { println(it) }.also { check(it == 2602) }
}
