fun main() {
    fun part1(input: List<String>) = caloriesList(input).max()

    fun part2(input: List<String>) = caloriesList(input).sortedDescending().take(3).sum()

    val input = readLines("Day01_test")

    println(part1(input))
    println(part2(input))
}

fun caloriesList(input: List<String>): List<Int> = input.fold(mutableListOf(0)) { calories, line ->
    calories.apply {
        if (line.isEmpty()) {
            add(0)
        } else {
            this[calories.lastIndex] += line.toInt()
        }
    }
}
