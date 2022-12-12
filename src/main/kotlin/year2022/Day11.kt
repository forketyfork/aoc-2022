package year2022

data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val divider: Long,
    val trueThrow: Int,
    val falseThrow: Int,
    var inspections: Long = 0
)

class Day11 {

    private fun parseMonkeys(input: String) = input.split("\n\n")
        .map { it.lines() }
        .map { lines ->
            Monkey(
                ArrayDeque(lines[1]
                    .substringAfter("Starting items: ")
                    .split(", ")
                    .map { it.toLong() }),
                lines[2]
                    .substringAfter("Operation: new = ")
                    .let {
                        if (it == "old * old") { a ->
                            a * a
                        } else if (it.startsWith("old * ")) { a ->
                            a * it.substringAfter("old * ").toLong()
                        } else { a ->
                            a + it.substringAfter("old + ").toLong()
                        }
                    },
                lines[3].substringAfter("Test: divisible by ").toLong(),
                lines[4].substringAfter("If true: throw to monkey ").toInt(),
                lines[5].substringAfter("If false: throw to monkey ").toInt(),
            )
        }

    private fun solution(monkeys: List<Monkey>, rounds: Int, worryReducer: (Long) -> Long): Long {
        repeat(rounds) {
            monkeys.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    val item = monkey.items.removeFirst()
                    monkey.inspections++
                    val newItem = worryReducer(monkey.operation(item))
                    monkeys[if (newItem % monkey.divider == 0L) monkey.trueThrow else monkey.falseThrow]
                        .items.add(newItem)
                }
            }
        }
        return monkeys.sortedByDescending(Monkey::inspections).let { (first, second) ->
            first.inspections * second.inspections
        }
    }

    fun part1(input: String) = solution(parseMonkeys(input), 20) { it / 3 }
    fun part2(input: String) = parseMonkeys(input).let { monkeys ->
        val mod = monkeys.map { it.divider }.reduce(Long::times)
        solution(monkeys, 10000) { it % mod }
    }

}
