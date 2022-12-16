package year2022

import utils.Direction.*
import utils.Point

class Day14 {

    private val origin = Point(500, 0)

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
            var sand = origin
            while (true) {
                sand = if (!set.contains(sand.move(DOWN))) {
                    sand.move(DOWN)
                } else if (!set.contains(sand.move(LEFT_DOWN))) {
                    sand.move(LEFT_DOWN)
                } else if (!set.contains(sand.move(RIGHT_DOWN))) {
                    sand.move(RIGHT_DOWN)
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
            var sand = origin
            while (true) {
                sand = if (!set.contains(sand.move(DOWN))) {
                    sand.move(DOWN)
                } else if (!set.contains(sand.move(LEFT_DOWN))) {
                    sand.move(LEFT_DOWN)
                } else if (!set.contains(sand.move(RIGHT_DOWN))) {
                    sand.move(RIGHT_DOWN)
                } else {
                    count++
                    if (sand == origin) {
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