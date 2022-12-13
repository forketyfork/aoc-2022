package year2015

import utils.Direction
import utils.Point
import utils.isEven

class Day03 {

    fun part1(input: String) = visited(input).size

    fun part2(input: String): Int {
        return input.withIndex()
            .partition { it.index.isEven() }
            .toList()
            .map { it -> it.map { it.value } }
            .map(Collection<Char>::toCharArray)
            .map(::String)
            .map(::visited)
            .reduce(Set<Point>::plus)
            .size
    }

    private fun visited(input: String): Set<Point> =
        input.map(Direction.Companion::byArrow)
            .runningFold(Point.ORIGIN, Point::move)
            .toSet()

}