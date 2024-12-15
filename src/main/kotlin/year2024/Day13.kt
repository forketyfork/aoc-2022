package year2024

class Day13 {

    companion object {
        val REGEX = "Button A: X\\+(\\d+), Y\\+(\\d+)\nButton B: X\\+(\\d+), Y\\+(\\d+)\nPrize: X=(\\d+), Y=(\\d+)".toRegex()
    }

    data class Equation(val ax: Long, val ay: Long, val bx: Long, val by: Long, val x: Long, val y: Long)

    fun part1(input: String) = toEquations(input).solve()
    fun part2(input: String) = toEquations(input, 10000000000000L).solve()

    fun toEquations(input: String, shift: Long = 0L) = input.split("\n\n").map {
        val values = REGEX.find(it)!!.groupValues
        val ax = values[1].toLong()
        val bx = values[2].toLong()
        val ay = values[3].toLong()
        val by = values[4].toLong()
        val x = values[5].toLong() + shift
        val y = values[6].toLong() + shift
        Equation(ax, bx, ay, by, x, y)
    }

    fun List<Equation>.solve(): Long = sumOf { (ax, ay, bx, by, x, y) ->
        val nb1 = x * ay - y * ax
        val nb2 = bx * ay - ax * by
        if (nb2 != 0L && nb1 % nb2 == 0L && nb1 / nb2 > 0) {
            val nb = nb1 / nb2
            if ((y - by * nb) % ay == 0L) {
                val na = (y - by * nb) / ay
                3 * na + nb
            } else 0
        } else {
            0
        }
    }

}
