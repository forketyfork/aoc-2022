package year2024

class Day09 {

    fun part1(input: String): Long {
        var occupied = true
        var ctr = 0
        var answer = 0L
        val fileCount = input.length / 2 + 1
        var fileFirst = 0
        var fileLast = fileCount - 1
        var fileLastCtr = (input[fileLast * 2] - '0')
        for (c in input.toCharArray()) {
            val digit = c - '0'
            if (occupied) {
                repeat(digit) {
                    answer += (ctr++) * fileFirst
                }
                fileFirst++
            } else {
                repeat(digit) {
                    answer += (ctr++) * fileLast
                    fileLastCtr--
                    if (fileLastCtr == 0) {
                        fileLast--
                        fileLastCtr = (input[fileLast * 2] - '0')
                    }
                }
            }
            occupied = !occupied
            if (fileFirst == fileLast) {
                repeat(fileLastCtr) {
                    answer += (ctr++) * fileLast
                }
                break
            }
        }
        return answer
    }

    class Node(val id: Int, val size: Int, var pad: Int)

    fun part2(input: String): Long {
        val nodes = input.toCharArray().toList().chunked(2).mapIndexed { idx, ch ->
            if (ch.size == 1) {
                Node(idx, ch[0] - '0', 0)
            } else {
                Node(idx, ch[0] - '0', ch[1] - '0')
            }
        }.toMutableList()

        var nodeToMove = nodes.size

        while (nodeToMove > 0) {
            nodeToMove--
            val p2 = nodes.indexOfLast { it.id == nodeToMove }
            for (p1 in 0..<p2) {
                if (nodes[p2].size <= nodes[p1].pad) {
                    val old = nodes[p1]
                    val new = nodes[p2]
                    val freed = new.pad + new.size
                    nodes.removeAt(p2)
                    nodes.add(p1 + 1, new)

                    new.pad = old.pad - new.size
                    old.pad = 0
                    nodes[p2].pad += freed


                    break
                }
            }
        }

        var answer = 0L
        var ctr = 0L
        for (node in nodes) {
            repeat(node.size) {
                answer += node.id * ctr
                ctr++
            }
            ctr += node.pad
        }

        return answer

    }
}
