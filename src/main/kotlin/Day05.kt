import java.util.*

class Day05(input: String) {

    data class CrateMove(val num: Int, val from: Int, val to: Int)

    private val lines = input.lines()

    private val emptyLine = lines.indexOfFirst { it.isEmpty() }

    private val stacks = lines.parseStacks()

    private val regex = Regex("\\d+")

    private val moves: List<CrateMove> = lines.drop(emptyLine + 1).map(::parseCrateMove)
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


