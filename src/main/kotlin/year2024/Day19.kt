package year2024


class Day19 {

    fun part1(input: String): Int {
        val (patterns, designs) = parse(input)
        buildMap {
            put("", 1L)
            return designs.count { design -> count(design, patterns) > 0 }
        }

    }

    fun part2(input: String): Long {
        val (patterns, designs) = parse(input)
        buildMap {
            put("", 1L)
            return designs.sumOf { design -> count(design, patterns) }
        }
    }

    fun MutableMap<String, Long>.count(design: String, patterns: List<String>): Long {
        return computeIfAbsent(design) { design ->
            patterns.filter { design.startsWith(it) }
                .sumOf { prefix ->
                    count(design.removePrefix(prefix), patterns)
                }
        }
    }

    fun parse(input: String): Pair<List<String>, List<String>> {
        val lines = input.lines()
        val patterns = lines.first().split(",\\s".toRegex())
        val designs = lines.drop(2)
        return patterns to designs
    }

}
