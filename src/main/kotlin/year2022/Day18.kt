package year2022

class Day18(input: String) {

    enum class Plane {
        XY, YZ, XZ
    }

    data class Face(val x: Int, val y: Int, val z: Int, val plane: Plane)

    data class Cube(val x: Int, val y: Int, val z: Int) {
        val faces = setOf(
            Face(x, y, z, Plane.XY),
            Face(x, y, z, Plane.XZ),
            Face(x, y, z, Plane.YZ),
            Face(x, y, z + 1, Plane.XY),
            Face(x, y + 1, z, Plane.XZ),
            Face(x + 1, y, z, Plane.YZ)
        )
    }

    private val cubes = input.lines().map { line ->
        line.split(",").map { it.toInt() }
    }.map { (x, y, z) -> Cube(x, y, z) }.toSet()

    fun part1(): Int {
        val nonTouchingFaces = mutableSetOf<Face>()
        cubes.flatMap { it.faces }.forEach { face ->
            if (!nonTouchingFaces.remove(face)) {
                nonTouchingFaces.add(face)
            }
        }
        return nonTouchingFaces.size
    }

    fun part2(): Int {
        val faces = cubes.flatMap { it.faces }.toSet()

        val maxX = cubes.maxOf { it.x } + 1
        val minX = cubes.minOf { it.x } - 1
        val maxY = cubes.maxOf { it.y } + 1
        val minY = cubes.minOf { it.y } - 1
        val maxZ = cubes.maxOf { it.z } + 1
        val minZ = cubes.minOf { it.z } - 1

        val visitedCubes = mutableSetOf<Cube>()
        val visitedFaces = mutableSetOf<Face>()
        with(ArrayDeque<Cube>()) {
            add(Cube(0, 0, 0))
            while (isNotEmpty()) {
                with(removeFirst()) {
                    if (this !in cubes && this !in visitedCubes) {
                        visitedCubes.add(this)
                        visitedFaces.addAll(faces.intersect(this.faces))
                        addAll(listOf(
                            copy(x = x + 1),
                            copy(x = x - 1),
                            copy(y = y + 1),
                            copy(y = y - 1),
                            copy(z = z + 1),
                            copy(z = z - 1)
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