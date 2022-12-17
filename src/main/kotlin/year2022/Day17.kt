package year2022

import utils.Direction
import utils.Direction.LEFT
import utils.Direction.RIGHT
import utils.isEven
import utils.nextIndexOf

class Day17 {

    // figure bitmaps are inverted top-down
    private val figures = listOf(
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

    private fun List<Int>.shiftLeft() = map { it shl 1 }
    private fun List<Int>.shiftRight() = map { it shr 1 }
    private fun List<Int>.canShiftLeft() = all { it < 0b1000000 }
    private fun List<Int>.canShiftRight() = all(Int::isEven)
    private fun List<Int>.overlaps(other: List<Int>) = zip(other).any { (first, second) -> first and second != 0 }

    fun part1(input: String) = solution(input, 2022)
    fun part2(input: String) = solution(input, 1000000000000)

    private data class Snapshot(val bucket: List<Int>, val dirPtr: Int, val figurePtr: Int)

    private fun solution(input: String, steps: Long): Long {
        val directions = input.map(Direction::byArrow)
        val bucket = MutableList(1) { 0b1111111 }

        var directionIdx = 0
        var figureIdx = 0
        var skippedHeight: Long = 0

        val observedSnapshots = mutableMapOf<Snapshot, Pair<Long, Long>>()

        var step = 0L

        var lookingForLoop = true

        while (step < steps) {
            var figure = figures[figureIdx].toList()
            repeat(3 + figure.size) { bucket.add(0) }
            var figureRowStart = bucket.size - figure.size

            // moving the figure down and sideways until it lands
            while (true) {
                when (directions[directionIdx]) {
                    LEFT -> if (figure.canShiftLeft()
                        && !figure.shiftLeft().overlaps(bucket.subList(figureRowStart, figureRowStart + figure.size))
                    ) {
                        figure = figure.shiftLeft()
                    }

                    RIGHT -> if (figure.canShiftRight()
                        && !figure.shiftRight().overlaps(bucket.subList(figureRowStart, figureRowStart + figure.size))
                    ) {
                        figure = figure.shiftRight()
                    }

                    else -> error("Unknown direction")
                }
                directionIdx = directionIdx.nextIndexOf(directions)
                figureRowStart--
                if (figure.overlaps(bucket.subList(figureRowStart, figureRowStart + figure.size))) {
                    figureRowStart++
                    break
                }
            }

            // painting the figure into the bucket
            figure.forEachIndexed { idx, row ->
                bucket[figureRowStart + idx] = bucket[figureRowStart + idx] or row
            }

            // removing the excessive height from the bucket
            val lowestRow = bfsLowestRow(bucket, MutableList(bucket.size) { 0 }, 1, bucket.lastIndex)
            repeat(lowestRow - 1) {
                bucket.removeAt(1)
            }
            skippedHeight += lowestRow - 1

            // removing empty rows from the bucket
            while (bucket.last() == 0) {
                bucket.removeLast()
            }

            // check if we've seen this place before...
            if (lookingForLoop) {
                val snapshot = Snapshot(bucket.toList(), directionIdx, figureIdx)
                observedSnapshots[snapshot]?.let { (loopStartingStep, loopStartingHeight) ->
                    val loopSize = step - loopStartingStep
                    val loopCount = ((steps - step) / loopSize) - 1
                    val currentTowerHeight = skippedHeight + bucket.size - 1
                    skippedHeight += (currentTowerHeight - loopStartingHeight) * loopCount
                    step += loopCount * loopSize // skip all loops
                    lookingForLoop = false // just let the regular algorithm finish the job for the rest of the steps
                }
                observedSnapshots[snapshot] = step to skippedHeight + bucket.size - 1L
            }

            figureIdx = figureIdx.nextIndexOf(figures)
            step++
        }
        return skippedHeight + bucket.size - 1
    }

    private fun bfsLowestRow(bucket: List<Int>, visited: MutableList<Int>, x: Int, y: Int): Int {
        if (x !in (1..0b1000000) || y < 1 || (visited[y] and x != 0)) {
            return Int.MAX_VALUE
        }
        visited[y] = visited[y] or x
        return if (bucket[y] and x != 0) {
            Int.MAX_VALUE
        } else {
            minOf(
                y,
                bfsLowestRow(bucket, visited, x shl 1, y),
                bfsLowestRow(bucket, visited, x shr 1, y),
                bfsLowestRow(bucket, visited, x, y - 1)
            )
        }
    }

}
