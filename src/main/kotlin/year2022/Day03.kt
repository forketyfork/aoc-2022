package year2022

class Day03 {

    private fun scorer(ch: Char) = 1 + when (ch) {
        in ('a'..'z') -> ch - 'a'
        else -> 26 + (ch - 'A')
    }

    fun part1(input: String) =
        input.lines().asSequence()
            .map { it.chunked(it.length / 2).map(String::toSet) }
            .map { (r1, r2) -> r1.intersect(r2) }
            .map(Set<Char>::first)
            .sumOf(::scorer)

    fun part2(input: String) =
        input.lines().asSequence()
            .map(String::toSet)
            .chunked(3)
            .map { (r1, r2, r3) -> r1.intersect(r2).intersect(r3) }
            .map(Set<Char>::first)
            .sumOf(::scorer)

}
