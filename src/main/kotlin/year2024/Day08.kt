package year2024

class Day08 {

    fun part1(input: String): Long {
        val map: MutableMap<Char, MutableSet<Pair<Int, Int>>> = mutableMapOf()
        val lines = input.lines().map { it.toCharArray() }

        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, c ->
                if (c != '.') {
                    map.computeIfAbsent(c) { mutableSetOf<Pair<Int, Int>>() }.add(i to j)
                }
            }
        }

        var answer = 0L
        map.forEach { c, set ->
            for (pair1 in set) {
                for (pair2 in set) {
                    if (pair1 != pair2) {

                        val top = if (pair1.first < pair2.first) pair1 else pair2
                        val bottom = if (pair1.first > pair2.first) pair1 else pair2
                        val left = if (pair1.second < pair2.second) pair1 else pair2
                        val right = if (pair1.second > pair2.second) pair1 else pair2

                        if (top == left) {
                            val y1 = 2 * top.first - bottom.first
                            val x1 = 2 * left.second - right.second
                            if (x1 in 0..<lines[0].size && y1 in 0..<lines.size && lines[y1][x1] != '#') {
                                answer++
                                lines[y1][x1] = '#'
                            }

                            val y2 = 2 * bottom.first - top.first
                            val x2 = 2 * right.second - left.second
                            if (x2 in 0..<lines[0].size && y2 in 0..<lines.size && lines[y2][x2] != '#') {
                                answer++
                                lines[y2][x2] = '#'
                            }

                        } else {
                            val y1 = 2 * top.first - bottom.first
                            val x1 = 2 * right.second - left.second
                            if (x1 in 0..<lines[0].size && y1 in 0..<lines.size && lines[y1][x1] != '#') {
                                answer++
                                lines[y1][x1] = '#'
                            }

                            val y2 = 2 * bottom.first - top.first
                            val x2 = 2 * left.second - right.second
                            if (x2 in 0..<lines[0].size && y2 in 0..<lines.size && lines[y2][x2] != '#') {
                                answer++
                                lines[y2][x2] = '#'
                            }
                        }
                    }
                }
            }

        }
        return answer
    }

    fun part2(input: String): Long {
        val map: MutableMap<Char, MutableSet<Pair<Int, Int>>> = mutableMapOf()
        val lines = input.lines().map { it.toCharArray() }

        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, c ->
                if (c != '.') {
                    map.computeIfAbsent(c) { mutableSetOf<Pair<Int, Int>>() }.add(i to j)
                }
            }
        }

        var answer = 0L
        map.forEach { c, set ->
            for (pair1 in set) {
                for (pair2 in set) {
                    if (pair1 != pair2) {
                        val top = if (pair1.first < pair2.first) pair1 else pair2
                        val bottom = if (pair1.first > pair2.first) pair1 else pair2
                        val left = if (pair1.second < pair2.second) pair1 else pair2
                        val right = if (pair1.second > pair2.second) pair1 else pair2

                        if (top == left) {
                            var ctr = 0
                            while (true) {
                                val y1 = top.first - ctr * (bottom.first - top.first)
                                val x1 = left.second - ctr * (right.second - left.second)
                                if (x1 in 0..<lines[0].size && y1 in 0..<lines.size) {
                                    if (lines[y1][x1] != '#') {
                                        answer++
                                        lines[y1][x1] = '#'
                                    }
                                    ctr++
                                } else {
                                    break
                                }
                            }

                            ctr = 0
                            while (true) {
                                val y2 = bottom.first - ctr * (top.first - bottom.first)
                                val x2 = right.second - ctr * (left.second - right.second)
                                if (x2 in 0..<lines[0].size && y2 in 0..<lines.size) {
                                    if (lines[y2][x2] != '#') {
                                        answer++
                                        lines[y2][x2] = '#'
                                    }
                                    ctr++
                                } else {
                                    break
                                }
                            }

                        } else {
                            var ctr = 0
                            while (true) {
                                val y1 = top.first - ctr * (bottom.first - top.first)
                                val x1 = right.second - ctr * (left.second - right.second)
                                if (x1 in 0..<lines[0].size && y1 in 0..<lines.size) {
                                    if (lines[y1][x1] != '#') {
                                        answer++
                                        lines[y1][x1] = '#'
                                    }
                                    ctr++
                                } else {
                                    break
                                }
                            }

                            ctr = 0
                            while (true) {
                                val y2 = bottom.first - ctr * (top.first - bottom.first)
                                val x2 = left.second - ctr * (right.second - left.second)
                                if (x2 in 0..<lines[0].size && y2 in 0..<lines.size) {
                                    if (lines[y2][x2] != '#') {
                                        answer++
                                        lines[y2][x2] = '#'
                                    }
                                    ctr++
                                } else {
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }

        return answer
    }

}
