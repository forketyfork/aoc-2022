package year2022

import utils.Point3D

class Day18(input: String) {

    enum class Plane {
        XY, YZ, XZ
    }

    data class Face(val point: Point3D, val plane: Plane)

    private val Point3D.faces
        get() = setOf(
            Face(this, Plane.XY),
            Face(this, Plane.XZ),
            Face(this, Plane.YZ),
            Face(this.move(dz = 1), Plane.XY),
            Face(this.move(dy = 1), Plane.XZ),
            Face(this.move(dx = 1), Plane.YZ)
        )

    private val cubes = input.lines().map(Point3D::parse).toSet()

    fun part1(): Int = with(mutableSetOf<Face>()) {
        cubes.flatMap { it.faces }.forEach { face ->
            if (!remove(face)) {
                add(face)
            }
        }
        return size
    }

    fun part2(): Int {
        val faces = cubes.flatMap { it.faces }.toSet()

        val maxX = cubes.maxOf { it.x } + 1
        val minX = cubes.minOf { it.x } - 1
        val maxY = cubes.maxOf { it.y } + 1
        val minY = cubes.minOf { it.y } - 1
        val maxZ = cubes.maxOf { it.z } + 1
        val minZ = cubes.minOf { it.z } - 1

        val visitedCubes = mutableSetOf<Point3D>()
        val visitedFaces = mutableSetOf<Face>()
        with(ArrayDeque<Point3D>()) {
            add(Point3D.ORIGIN)
            while (isNotEmpty()) {
                with(removeFirst()) {
                    if (this !in cubes && this !in visitedCubes) {
                        visitedCubes.add(this)
                        visitedFaces.addAll(faces.intersect(this.faces))
                        addAll(listOf(
                            move(dx = 1),
                            move(dx = -1),
                            move(dy = 1),
                            move(dy = -1),
                            move(dz = 1),
                            move(dz = -1)
                        ).filter {
                            it.x in minX..maxX
                                    && it.y in minY..maxY
                                    && it.z in minZ..maxZ
                        })
                    }
                }
            }
        }
        return visitedFaces.size
    }
}
