package year2022

import utils.Direction.*
import utils.Point

class Day14 {

    private fun parse(input: String): MutableSet<Point> = buildSet {
        input.lines().forEach { line ->
            line.split(" -> ")
                .windowed(2)
                .map { it.map(Point::parse) }
                .forEach { (p1, p2) ->
                    add(p1)
                    var p1m = p1
                    while (p1m != p2) {
                        p1m = if (p1m.x < p2.x) {
                            p1m.move(RIGHT)
                        } else if (p1m.x > p2.x) {
                            p1m.move(LEFT)
                        } else if (p1m.y < p2.y) {
                            p1m.move(DOWN)
                        } else {
                            p1m.move(UP)
                        }
                        add(p1m)
                    }
                }
        }
    }.toMutableSet()


    fun part1(input: String): Int {
        val set = parse(input)
        val xRange = (set.minOf { it.x }..set.maxOf { it.x })
        val yRange = (0..set.maxOf { it.y })
        var count = 0
        while (true) {
            var sand = Point(500, 0)
            while (true) {
                sand = if (!set.contains(sand.copy(y = sand.y + 1))) {
                    sand.copy(y = sand.y + 1)
                } else if (!set.contains(sand.copy(x = sand.x - 1, y = sand.y + 1))) {
                    sand.copy(x = sand.x - 1, y = sand.y + 1)
                } else if (!set.contains(sand.copy(x = sand.x + 1, y = sand.y + 1))) {
                    sand.copy(x = sand.x + 1, y = sand.y + 1)
                } else {
                    count++
                    set.add(sand)
                    break
                }
                if (sand.x !in xRange || sand.y !in yRange) {
                    return count
                }
            }
        }
    }

    fun part2(input: String): Int {
        val set = parse(input)
        val bottom = set.maxOf { it.y } + 1
        var count = 0
        while (true) {
            var sand = Point(500, 0)
            while (true) {
                sand = if (!set.contains(sand.copy(y = sand.y + 1))) {
                    sand.copy(y = sand.y + 1)
                } else if (!set.contains(sand.copy(x = sand.x - 1, y = sand.y + 1))) {
                    sand.copy(y = sand.y + 1, x = sand.x - 1)
                } else if (!set.contains(sand.copy(x = sand.x + 1, y = sand.y + 1))) {
                    sand.copy(x = sand.x + 1, y = sand.y + 1)
                } else {
                    count++
                    if (sand == Point(500, 0)) {
                        return count
                    }
                    set.add(sand)
                    break
                }
                if (sand.y == bottom) {
                    count++
                    set.add(sand)
                    break
                }
            }
        }
    }

}