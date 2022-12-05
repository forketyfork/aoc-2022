import java.util.*


class Crane(input: List<String>) {

    data class CrateMove(val num: Int, val from: Int, val to: Int)

    private val emptyLine = input.indexOfFirst { it.isEmpty() }

    private val stacks = input.parseStacks()

    private val regex = Regex("\\d+")

    private val moves: List<CrateMove> = input.drop(emptyLine + 1).map(::parseCrateMove)
    private fun List<String>.parseStacks() = MutableList<Stack<Char>>((first().length + 1) / 4) { Stack() }
        .also { stacks ->
            take(emptyLine - 1).reversed()
                .flatMap { it.chunked(4) }
                .map { it[1] }
                .forEachIndexed { idx, char ->
                    if (char != ' ') {
                        stacks[idx % stacks.size].push(char)
                    }
                }
        }

    private fun parseCrateMove(command: String): CrateMove {
        val (num, from, to) = regex.findAll(command).map { it.value.toInt() }.toList()
        return CrateMove(num, from - 1, to - 1)
    }

    private fun moveAll(mover: (move: CrateMove) -> Unit): String {
        moves.forEach(mover)
        return String(stacks.map { it.pop() }.toCharArray())
    }

    fun part1() = moveAll { move -> move.moveOneByOne() }
    fun part2() = moveAll { move -> move.moveAtOnce() }

    private fun CrateMove.moveOneByOne() = repeat(num) {
        stacks[to].push(stacks[from].pop())
    }

    private fun CrateMove.moveAtOnce() = Stack<Char>().apply {
        repeat(num) {
            push(stacks[from].pop())
        }
        repeat(num) {
            stacks[to].push(pop())
        }
    }

}


fun main() {
    val input = readInput("Day05_test")
    Crane(input).part1().also { println(it) }.also { check(it == "CNSZFDVLJ") }
    Crane(input).part2().also { println(it) }.also { check(it == "QNDWLMGNS") }
}
