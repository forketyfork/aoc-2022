package year2023

class Day04 {

    fun Pair<Set<Int>, Set<Int>>.wins() = first.intersect(second).size

    fun part1(input: String): Int {
        return parseCards(input)
            .map { 1 shl (it.wins() - 1) }
            .sum()
    }

    fun part2(input: String): Int {
        val cards = parseCards(input)
        val cardCounts = IntArray(cards.size) { 1 }
        val cardWins = IntArray(cards.size)
        cards.forEachIndexed { idx, card ->
            cardWins[idx] = card.wins()
            for (i in (idx + 1)..<(idx + 1 + cardWins[idx])) {
                cardCounts[i] += cardCounts[idx]
            }
        }
        return cardCounts.sum()
    }

    fun parseCards(input: String): List<Pair<Set<Int>, Set<Int>>> {
        return input.lines()
            .filter { it.isNotBlank() }
            .map { line ->
                val (winning, mine) = line.split(':')[1]
                    .split('|')
                    .map {
                        it.split(' ')
                            .filter { it.isNotBlank() }
                            .map { it.toInt() }
                            .toSet()
                    }
                winning to mine
            }
    }

}