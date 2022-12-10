import java.util.concurrent.atomic.AtomicInteger

class Day08 {

    fun part1(input: String): Int {
        val array = input.lines().map { it.chars().toArray() }.toTypedArray()

        val visibility = Array(array.size) {
            Array(array[0].size) { false }
        }

        var count = 0

        fun AtomicInteger.updateVisibility(i: Int, j: Int) {
            if (array[i][j] > this.get()) {
                if (!visibility[i][j]) {
                    count++
                    visibility[i][j] = true
                }
                this.set(array[i][j])
            }
        }

        for (i in array.indices) {
            AtomicInteger(-1).apply { array.indices.forEach { updateVisibility(i, it) } }
            AtomicInteger(-1).apply { array.indices.reversed().forEach { updateVisibility(i, it) } }
            AtomicInteger(-1).apply { array.indices.forEach { updateVisibility(it, i) } }
            AtomicInteger(-1).apply { array.indices.reversed().forEach { updateVisibility(it, i) } }
        }

        return count
    }

    fun part2(input: String): Int {
        val array = input.lines().map { it.chars().toArray() }.toTypedArray()
        return (1 until array.lastIndex).maxOf { i ->
            (1 until array.lastIndex).maxOf { j ->
                val height = array[i][j]
                var left = (i - 1 downTo 0).takeWhile { array[it][j] < height }.size
                if (left < i) left++
                var right = (i + 1 until array.size).takeWhile { array[it][j] < height }.size
                if (right < array.lastIndex - i) right++
                var top = (j - 1 downTo 0).takeWhile { array[i][it] < height }.size
                if (top < j) top++
                var bottom = (j + 1 until array.size).takeWhile { array[i][it] < height }.size
                if (bottom < array.lastIndex - j) bottom++
                left * right * top * bottom
            }
        }
    }
}
