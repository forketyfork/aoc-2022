class Day01 {
    fun part1(input: String) = caloriesList(input).max()

    fun part2(input: String) = caloriesList(input).sortedDescending().take(3).sum()
    private fun caloriesList(input: String): List<Int> = input.lines().fold(mutableListOf(0)) { calories, line ->
        calories.apply {
            if (line.isEmpty()) {
                add(0)
            } else {
                this[calories.lastIndex] += line.toInt()
            }
        }
    }
}
