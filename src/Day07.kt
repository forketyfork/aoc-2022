import java.lang.RuntimeException

class Directory(val parent: Directory?) {
    var totalSize: Int = 0
    val childDirs: MutableMap<String, Directory> = mutableMapOf()

    fun find(predicate: (Directory) -> Boolean): List<Directory> = buildList {
        if (predicate(this@Directory)) {
            add(this@Directory)
        }
        addAll(childDirs.values.flatMap { dir -> dir.find(predicate) })
    }

    fun updateTotalSizes(): Int {
        totalSize += childDirs.values.sumOf { it.updateTotalSizes() }
        return totalSize
    }
}

fun buildGraph(input: List<String>) = Directory(null).apply {
    var current: Directory = this
    input.forEach { line ->
        current = when {
            line == "$ cd /" -> this
            line == "$ ls" -> current
            line == "$ cd .." -> current.parent!!
            line.startsWith("$ cd ") -> current.childDirs[line.substringAfter("$ cd ")]!!
            line.startsWith("dir ") -> current.apply {
                childDirs[line.substringAfter("dir ")] = Directory(current)
            }

            line[0].isDigit() -> current.apply { totalSize += line.split(" ")[0].toInt() }
            else -> throw RuntimeException("Can't parse: $line")
        }
    }
    updateTotalSizes()
}

fun part1(input: List<String>): Int = buildGraph(input)
    .find { it.totalSize <= 100000 }
    .sumOf { it.totalSize }

fun part2(input: List<String>): Int {
    val root = buildGraph(input)
    val excess = 30000000 - (70000000 - root.totalSize)
    return root.find { it.totalSize >= excess }
        .minBy { it.totalSize }
        .totalSize
}

fun main() {

    val input = readLines("Day07_test")

    part1(input).also { println(it) }.also { check(it == 1390824) }
    part2(input).also { println(it) }.also { check(it == 7490863) }
}
