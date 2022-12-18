package year2022

import utils.Direction.*
import utils.Point2D

data class Sensor(val position: Point2D, val beacon: Point2D) {
    val coverage = position.manhattan(beacon)
}

class Day15(input: String) {

    private val regex = """Sensor at x=(.*), y=(.*): closest beacon is at x=(.*), y=(.*)""".toRegex()

    private val sensors = input.lines()
        .map { regex.find(it)!!.groupValues.drop(1).map(String::toInt) }
        .map { (xs, ys, xb, yb) -> Sensor(Point2D(xs, ys), Point2D(xb, yb)) }

    private val xl = sensors.minOf { minOf(it.position.x, it.beacon.x, it.position.x - it.coverage) }
    private val xr = sensors.maxOf { maxOf(it.position.x, it.beacon.x, it.position.x + it.coverage) }

    fun part1(row: Int) = (xl..xr).count { col ->
        Point2D(col, row).let { point ->
            sensors.any {
                it.beacon != point && it.position.manhattan(point) <= it.coverage
            }
        }
    }

    fun part2(limit: Int): Long {

        fun check(point: Point2D) = point.x in (0..limit)
                && point.y in (0..limit)
                && sensors.none { it.position.manhattan(point) <= it.coverage }

        sensors.forEach { sensor ->
            with(sensor.position) {
                listOf(
                    move(dx = -sensor.coverage - 1),
                    move(dy = -sensor.coverage - 1),
                    move(dx = sensor.coverage + 1),
                    move(dy = sensor.coverage + 1),
                    move(dx = -sensor.coverage - 1)
                ).windowed(2)
                    .zip(listOf(RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP))
                    .forEach { (points, dir) ->
                        var (current, target) = points
                        while (current != target) {
                            if (check(current)) {
                                return current.x.toLong() * 4000000 + current.y.toLong()
                            }
                            current = current.move(dir)
                        }
                    }
            }
        }
        error("Should have found a single one!")
    }

}