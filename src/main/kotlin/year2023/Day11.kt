package year2023

import utils.Point2D

class Day11 {

    fun part1(input: String) = solve(input, 2L)

    fun part2(input: String) = solve(input, 1000000L)

    fun solve(input: String, shift: Long): Long {
        val space = input.lines().map { it.toCharArray() }

        space.forEach { row ->
            if (row.all { it == '.' }) {
                row.indices.forEach { col -> row[col] = 'v' }
            }
        }
        space[0].indices.forEach { colIdx ->
            if (space.all { row -> row[colIdx] == '.' || row[colIdx] == 'v' }) {
                space.forEach { row -> row[colIdx] = 'v' }
            }
        }

        val points = space.flatMapIndexed { rowIdx, row ->
            row.mapIndexed { colIdx, col ->
                if (col == '#') {
                    Point2D(colIdx, rowIdx)
                } else {
                    null
                }
            }.filterNotNull()
        }

        return points.indices.flatMap { idx1 ->
            points.indices.drop(idx1).map { idx2 ->
                var p1 = points[idx1]
                val p2 = points[idx2]
                var counter = 0L

                while (p1 != p2) {
                    p1 = if (p1.x < p2.x) {
                        p1.move(1, 0)
                    } else if (p1.x > p2.x) {
                        p1.move(-1, 0)
                    } else if (p1.y < p2.y) {
                        p1.move(0, 1)
                    } else {
                        p1.move(0, -1)
                    }
                    counter = if (space[p1.y][p1.x] == 'v') {
                        counter + shift
                    } else {
                        counter + 1L
                    }
                }
                counter
            }
        }.sum()

    }

}
