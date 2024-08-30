package year2023

class Day05 {

    data class RangeMap(val destStart: Long, val sourceStart: Long, val size: Long) {

        private val sourceRange = sourceStart..<sourceStart + size

        companion object {
            fun parse(input: String): RangeMap {
                val (destStart, sourceStart, size) = input.split(' ').map { it.toLong() }
                return RangeMap(destStart, sourceStart, size)
            }
        }

        fun getDestination(source: Long): Long? {
            return if (source in sourceRange) {
                destStart + (source - sourceStart)
            } else {
                null
            }
        }
    }

    fun part1(input: String): Long {
        val lines = input.lines()
        val seeds = lines[0]
            .substringAfter("seeds: ")
            .split(' ')
            .map { it.toLong() }
            .map { it..it }
        return findMinLocation(lines, seeds)
    }

    fun part2(input: String): Long {
        val lines = input.lines()
        val seeds = lines[0]
            .substringAfter("seeds: ")
            .split(' ')
            .map { it.toLong() }
            .chunked(2)
            .map { chunk ->
                val (start, size) = chunk
                start..<start + size
            }
        return findMinLocation(lines, seeds)
    }

    private fun findMinLocation(lines: List<String>, seedRanges: List<LongRange>): Long {
        val mappings = buildList {
            var currentList: MutableList<RangeMap>? = null
            for (line in lines.drop(1)) {
                if (line.isBlank()) {
                    currentList = mutableListOf()
                    add(currentList as List<RangeMap>)
                } else if (line[0].isDigit()) {
                    currentList!!.add(RangeMap.parse(line))
                }
            }
        }

        return seedRanges.map { seedRange ->
            seedRange.minOf { seed ->
                mappings.fold(seed) { acc, rangeMaps ->
                    rangeMaps.forEach { rangeMap ->
                        val destination = rangeMap.getDestination(acc)
                        if (destination != null) {
                            return@fold destination
                        }
                    }
                    acc
                }
            }
        }.min()
    }

}
