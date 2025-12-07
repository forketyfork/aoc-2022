package year2025

import utils.CharGrid

class Day07 {

    fun part1(input: String): Long {
        val grid = CharGrid(input)
        val beams = mutableSetOf(grid.row(0).indexOf('S'))
        return grid.rows().drop(0).fold(0) { acc, row ->
            val indicesOfSplitters = (0..<grid.width).filter { idx -> row[idx] == '^' }.toSet()
            val splitters = indicesOfSplitters.intersect(beams)
            splitters.forEach { splitter ->
                beams.remove(splitter)
                if (splitter > 0) {
                    beams.add(splitter - 1)
                }
                if (splitter + 1 < grid.width) {
                    beams.add(splitter + 1)
                }
            }
            acc + splitters.size
        }
    }

    fun part2(input: String): Long {
        val grid = CharGrid(input)
        val beams = mutableSetOf(grid.row(0).indexOf('S'))
        val ways = Array(grid.width) { 0L }
        ways[beams.first()] = 1
        return grid.rows().drop(0).fold(ways) { ways, row ->
            val indicesOfSplitters = (0..<grid.width).filter { idx -> row[idx] == '^' }.toSet()
            val splitters = indicesOfSplitters.intersect(beams)
            if (splitters.isEmpty()) {
                ways
            } else {
                val nextWays = Array(grid.width) { 0L }
                (beams - splitters).forEach { beam -> nextWays[beam] = ways[beam] }
                splitters.forEach { splitter ->
                    beams.remove(splitter)
                    if (splitter > 0) {
                        beams.add(splitter - 1)
                        nextWays[splitter - 1] += ways[splitter]
                    }
                    if (splitter + 1 < grid.width) {
                        beams.add(splitter + 1)
                        nextWays[splitter + 1] += ways[splitter]
                    }
                }
                nextWays
            }
        }.sum()
    }

}
