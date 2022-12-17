package year2022

import utils.Direction
import utils.Direction.LEFT
import utils.Direction.RIGHT
import utils.isEven

class Day17 {

    private fun List<Int>.shiftLeft() = map { it shl 1 }
    private fun List<Int>.shiftRight() = map { it shr 1 }

    private fun List<Int>.canShiftLeft() = all { it < 0b1000000 }
    private fun List<Int>.canShiftRight() = all(Int::isEven)
    private fun List<Int>.overlaps(other: List<Int>) = zip(other).any { (first, second) -> first and second != 0 }

    fun part1(input: String): Int {
        val directions = input.map(Direction::byArrow)
        val bucket = MutableList(1) { 0b1111111 }
        val figures = listOf(
            listOf(
                0b0011110
            ),
            listOf(
                0b0001000,
                0b0011100,
                0b0001000
            ),
            listOf(
                0b0011100,
                0b0000100,
                0b0000100,
            ),
            listOf(
                0b0010000,
                0b0010000,
                0b0010000,
                0b0010000
            ),
            listOf(
                0b0011000,
                0b0011000
            )
        )

        var dirPtr = 0
        var figurePtr = 0

        repeat(2022) {

            var figure = figures[figurePtr].toList()
            repeat(3 + figure.size) { bucket.add(0) }
            var figureRow = bucket.size - figure.size

            printBucket(bucket, figure, figureRow)

            while (true) {
                when (directions[dirPtr]) {
                    LEFT -> if (figure.canShiftLeft()
                        && !figure.shiftLeft().overlaps(bucket.subList(figureRow, figureRow + figure.size))
                    ) {
                        figure = figure.shiftLeft()
                    }

                    RIGHT -> if (figure.canShiftRight()
                        && !figure.shiftRight().overlaps(bucket.subList(figureRow, figureRow + figure.size))
                    ) {
                        figure = figure.shiftRight()
                    }

                    else -> error("")
                }
                dirPtr = (dirPtr + 1) % directions.size
                figureRow--
                if (figure.first() and bucket[figureRow] != 0) {
                    figureRow++
                    break
                }
            }
            for (row in figure) {
                bucket[figureRow] = bucket[figureRow++] or row
            }
            while (bucket.last() == 0) {
                bucket.removeLast()
            }
            figurePtr = (figurePtr + 1) % figures.size
        }

        return bucket.size - 1
    }

    private fun printBucket(bucket: List<Int>, figure: List<Int>, figurePos: Int) {
        for (i in bucket.size downTo 1) {
            print("🟦")
            repeat(7) {
                if (i >= figurePos && i - figurePos < figure.size && figure[i - figurePos] and (0b1000000 shr it) != 0) {
                    print("🟥")
                } else if (i < bucket.size && bucket[i] and (0b1000000 shr it) != 0) {
                    print("⬜")
                } else {
                    print("⬛️")
                }
            }
            println("🟦")
        }
        println("🟦🟦🟦🟦🟦🟦🟦🟦🟦\n")
    }


    fun part2(input: String): Int {
        return 0
    }

}
