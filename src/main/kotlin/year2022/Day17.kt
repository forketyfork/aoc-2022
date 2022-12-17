package year2022

import utils.Direction
import utils.Direction.LEFT
import utils.Direction.RIGHT

class Day17 {

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
        println("🟦🟦🟦🟦🟦🟦🟦🟦🟦")
    }

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
            println("The ${it + 1} rock begins falling")
            var figure = figures[figurePtr].toList()
            repeat(3 + figure.size) { bucket.add(0) }
            var figurePos = bucket.size - figure.size
            printBucket(bucket, figure, figurePos)
            while (true) {
                when (directions[dirPtr]) {
                    LEFT -> if (figure.all { row -> row and 0b1000000 == 0 }
                        && figure.filterIndexed { idx, row -> (row shl 1) and bucket[figurePos + idx] != 0 }.isEmpty()
                    ) {
                        figure = figure.map { it shl 1 }
                        println("Jet of gas pushes rock left")
                        printBucket(bucket, figure, figurePos)
                    } else {
                        println("Jet of gas pushes rock left but nothing happens")
                        printBucket(bucket, figure, figurePos)
                    }

                    RIGHT -> if (figure.all { row -> row and 0b0000001 == 0 }
                        && figure.filterIndexed { idx, row -> (row shr 1) and bucket[figurePos + idx] != 0 }.isEmpty()
                    ) {
                        figure = figure.map { it shr 1 }
                        println("Jet of gas pushes rock right")
                        printBucket(bucket, figure, figurePos)
                    } else {
                        println("Jet of gas pushes rock right but nothing happens")
                        printBucket(bucket, figure, figurePos)
                    }

                    else -> error("")
                }
                dirPtr = (dirPtr + 1) % directions.size
                figurePos--
                if (figure.first() and bucket[figurePos] != 0) {
                    figurePos++
                    println("Rock falls 1 unit, causing it to come to rest")
                    printBucket(bucket, figure, figurePos)
                    break
                }
                println("Rock falls 1 unit")
                printBucket(bucket, figure, figurePos)
            }
            for (row in figure) {
                bucket[figurePos] = bucket[figurePos++] or row
            }
            while (bucket.last() == 0) {
                bucket.removeLast()
            }
            figurePtr = (figurePtr + 1) % figures.size
        }
        return bucket.size - 1
    }


    fun part2(input: String): Int {
        return 0
    }

}
