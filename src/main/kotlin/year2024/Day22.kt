package year2024

class Day22 {

    fun part1(input: String) = calculatePrices(input).sum()

    fun calculatePrices(input: String, onSecretUpdate: (Int, Int, Long, Long) -> Unit = { _, _, _, _ -> }): List<Long> {
        val nums = input.lines().map { it.toLong() }.toMutableList()
        repeat(2000) { iteration ->
            nums.forEachIndexed { sequenceIdx, oldSecret ->
                var newSecret = oldSecret

                val mul64 = newSecret shl 6
                newSecret = (newSecret xor mul64) % 16777216L

                val num32 = newSecret shr 5
                newSecret = (newSecret xor num32) % 16777216L

                val mul2048 = newSecret shl 11
                newSecret = (newSecret xor mul2048) % 16777216L

                onSecretUpdate(iteration, sequenceIdx, oldSecret, newSecret)

                nums[sequenceIdx] = newSecret
            }
        }
        return nums
    }

    fun part2(input: String): Int {

        val sequences = Array(input.lines().size) { Array(2000) { arrayOf(0, 0) } }

        calculatePrices(input) { iteration, sequenceIdx, oldSecret, newSecret ->
            val price = (newSecret % 10).toInt()
            val diff = (price - (oldSecret % 10)).toInt()

            // using 0x0A0A0A0A as the first value since it can never come up in the real sequence
            sequences[sequenceIdx][iteration][0] =
                ((if (iteration == 0) 0x0A0A0A0A else sequences[sequenceIdx][iteration - 1][0]) shl 8) xor diff
            sequences[sequenceIdx][iteration][1] = price
        }

        val maps = sequences.map { iterations -> iterations.reversed().associate { it[0] to it[1] } }

        return buildMap {
            maps.forEach { map ->
                map.forEach { (key, value) -> merge(key, value, Int::plus) }
            }
        }.values.max()
    }
}
