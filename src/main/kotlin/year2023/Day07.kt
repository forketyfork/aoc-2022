package year2023

import kotlin.reflect.KClass

class Day07 {

    enum class HandType {
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfAKind,
        FullHouse,
        FourOfAKind,
        FiveOfAKind
    }

    interface CardType<T : CardType<T>> : Comparable<T>

    enum class CardType1 : CardType<CardType1> { `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T, J, Q, K, A }
    enum class CardType2 : CardType<CardType2> { J, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T, Q, K, A }

    class Hand<T : CardType<T>>(val string: String, klass: KClass<T>) {
        val cards: List<T> = string.map {
            val parser = if (klass == CardType1::class) CardType1::valueOf else CardType2::valueOf
            parser(it.toString()) as T
        }
        val type: HandType = let {
            val cardCounts = cards.groupBy { it }.mapValues { it.value.size }.let { it ->
                if (klass == CardType2::class) {
                    val map = it as Map<CardType2, Int>
                    if (map.containsKey(CardType2.J)) {
                        val jokerCount = map[CardType2.J]!!
                        if (jokerCount == 5) {
                            return@let it
                        }
                        val maxOccurrenceCard = map.filter { it.key != CardType2.J }
                            .toSortedMap { a, b -> map[a]!!.compareTo(map[b]!!) }
                            .lastKey()!!
                        return@let map.filter { it.key != CardType2.J }.mapValues { (key, value) ->
                            if (key == maxOccurrenceCard) {
                                value + jokerCount
                            } else {
                                value
                            }
                        }
                    }
                }
                it
            }

            when (cardCounts.size) {
                1 -> HandType.FiveOfAKind
                2 -> if (cardCounts.containsValue(4)) {
                    HandType.FourOfAKind
                } else {
                    HandType.FullHouse
                }

                3 -> if (cardCounts.containsValue(3)) {
                    HandType.ThreeOfAKind
                } else {
                    HandType.TwoPair
                }

                4 -> HandType.OnePair
                else -> HandType.HighCard
            }
        }
    }

    fun part1(input: String) = solve(input, CardType1::class)
    fun part2(input: String) = solve(input, CardType2::class)

    fun <T : CardType<T>> solve(input: String, cardTypeKlass: KClass<T>): Long {
        return parseInput(input, cardTypeKlass).sortedWith { hand1, hand2 ->
            hand1.first.type.compareTo(hand2.first.type).let { compareResult ->
                if (compareResult != 0) {
                    compareResult
                } else {
                    hand1.first.cards.zip(hand2.first.cards).forEach { cardPair ->
                        cardPair.first.compareTo(cardPair.second).let { compareResult ->
                            if (compareResult != 0) {
                                return@sortedWith compareResult
                            }
                        }
                    }
                    0
                }
            }
        }.mapIndexed { idx, hand -> hand.second * (idx + 1) }.sum()
    }

    fun <T : CardType<T>> parseInput(input: String, klass: KClass<T>): List<Pair<Hand<T>, Long>> {
        return input.lines()
            .map { it.split(' ').let { Pair(Hand(it[0], klass), it[1].toLong()) } }
    }

}
