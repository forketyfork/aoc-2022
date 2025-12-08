package year2025

import utils.Point3D

class Day08 {

    fun part1(input: String, iterations: Int): Long {
        val points = parse(input)
        val closestPairs = getClosestPairs(points).take(iterations)
        val sets = points.map { setOf(it) }.toMutableSet()
        closestPairs.forEach { (point1, point2) ->
            joinSetsWithPoints(sets, point1, point2)
        }
        return sets.sortedByDescending { it.size }
            .take(3)
            .map { it.size }
            .fold(1L, Long::times)
    }

    fun part2(input: String): Long {
        val points = parse(input)
        val closestPairs = getClosestPairs(points)
        val sets = points.map { setOf(it) }.toMutableSet()
        closestPairs.forEach { (point1, point2) ->
            joinSetsWithPoints(sets, point1, point2)
            if (sets.size == 1) {
                return 1L * point1.x * point2.x
            }
        }
        error("Should never happen")
    }

    private fun parse(input: String) = input.lines().map {
        val (x, y, z) = it.split(",").map(String::toInt)
        Point3D(x, y, z)
    }

    private fun getClosestPairs(points: List<Point3D>): List<Pair<Point3D, Point3D>> =
        (0..<points.lastIndex).flatMap { idx1 ->
            (idx1 + 1..points.lastIndex).map { idx2 ->
                points[idx1] to points[idx2]
            }
        }.sortedBy { (point1, point2) -> point1.distanceFrom(point2) }

    private fun joinSetsWithPoints(sets: MutableSet<Set<Point3D>>, point1: Point3D, point2: Point3D) {
        val set1 = sets.find { it.contains(point1) }!!
        val set2 = sets.find { it.contains(point2) }!!
        if (set1 != set2) {
            sets.remove(set1)
            sets.remove(set2)
            sets.add(set1 + set2)
        }
    }

    private fun Point3D.distanceFrom(other: Point3D): Long =
        1L * (x - other.x) * (x - other.x) + 1L * (y - other.y) * (y - other.y) + 1L * (z - other.z) * (z - other.z)

}
