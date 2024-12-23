package year2024


class Day19 {

    fun part1(input: String): Int {
        val (patterns, designs) = parse(input)
        return designs.count { design -> canBeBuiltFrom(design, patterns.toSet()) }
    }

    fun part2(input: String): Long {
        return 0L
    }

    fun parse(input: String): Pair<List<String>, List<String>> {
        val lines = input.lines()
        val patterns = lines.first().split(",\\s".toRegex())
        val designs = lines.drop(2)
        return patterns to designs
    }

    fun canBeBuiltFrom(design: String, patterns: Set<String>): Boolean {
        if (design.isEmpty() || design in patterns) {
            return true
        }

        val prefixes = patterns.filter { design.startsWith(it) }
        val suffixes = patterns.filter { design.endsWith(it) }

        if (prefixes.isEmpty() || suffixes.isEmpty()) {
            return false
        }

        return prefixes.any { prefix ->
            suffixes.any { suffix ->
                canBeBuiltFrom(design.removePrefix(prefix).removeSuffix(suffix), patterns)
            }
        }
    }

}
