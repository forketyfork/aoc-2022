package year2022

import utils.Direction
import utils.Direction.*
import utils.Point2D

class Day23(contents: String) {

    private val directions = ArrayDeque<Direction>().apply {
        add(UP)
        add(DOWN)
        add(LEFT)
        add(RIGHT)
    }

    private var placement = buildSet {
        contents.lines().forEachIndexed { row, line ->
            line.forEachIndexed { col, char ->
                if (char == '#') {
                    add(Point2D(col, row))
                }
            }
        }
    }

    private fun Point2D.hasN() = placement.contains(move(UP))
    private fun Point2D.hasNE() = placement.contains(move(RIGHT_UP))
    private fun Point2D.hasE() = placement.contains(move(RIGHT))
    private fun Point2D.hasSE() = placement.contains(move(RIGHT_DOWN))
    private fun Point2D.hasS() = placement.contains(move(DOWN))
    private fun Point2D.hasSW() = placement.contains(move(LEFT_DOWN))
    private fun Point2D.hasW() = placement.contains(move(LEFT))
    private fun Point2D.hasNW() = placement.contains(move(LEFT_UP))
    private fun Point2D.hasNeighbors() =
        hasN() || hasNE() || hasE() || hasSE() || hasS() || hasSW() || hasW() || hasNW()


    fun part1(): Int {
        repeat(10) { round() }

        return with(placement) {
            (maxOf { it.x } - minOf { it.x } + 1) * (maxOf { it.y } - minOf { it.y } + 1) - size
        }
    }

    fun part2() = generateSequence(true) { round() }.takeWhile { it }.count()

    private fun round(): Boolean {

        var result = false
        buildMap<Point2D, MutableList<Point2D>> {
            // step 1
            placement.forEach { elf ->
                if (elf.hasNeighbors()) {
                    val proposal = directions.firstOrNull {
                        when (it) {
                            LEFT -> !elf.hasW() && !elf.hasNW() && !elf.hasSW()
                            RIGHT -> !elf.hasE() && !elf.hasNE() && !elf.hasSE()
                            UP -> !elf.hasN() && !elf.hasNE() && !elf.hasNW()
                            DOWN -> !elf.hasS() && !elf.hasSE() && !elf.hasSW()
                            else -> error("Unknown direction $it")
                        }
                    }?.let(elf::move) ?: elf
                    getOrPut(proposal) { mutableListOf() }.add(elf)
                } else {
                    getOrPut(elf) { mutableListOf() }.add(elf)
                }
            }
            // step 2
            placement = buildSet {
                this@buildMap.forEach { (newPoint, candidates) ->
                    if (candidates.size == 1) {
                        add(newPoint)
                        if (newPoint != candidates[0]) {
                            result = true
                        }
                    } else {
                        addAll(candidates)
                        result = true
                    }
                }
            }
        }

        directions.addLast(directions.removeFirst())
        return result
    }
}
