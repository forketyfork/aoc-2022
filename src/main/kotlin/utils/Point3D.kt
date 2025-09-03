package utils

data class Point3D(val x: Int, val y: Int, val z: Int) {

    fun move(dx: Int = 0, dy: Int = 0, dz: Int = 0) = copy(x = x + dx, y = y + dy, z = z + dz)

    companion object {
        val ORIGIN = Point3D(0, 0, 0)
        fun parse(string: String): Point3D {
            val (x, y, z) = string.split(",").map(String::toInt)
            return Point3D(x, y, z)
        }
    }
}
