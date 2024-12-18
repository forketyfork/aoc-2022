package year2024

class Day17 {

    class Computer(val inA: Long, val inB: Long, val inC: Long, val program: List<Long>, val search: Boolean = false) {

        private var ip = 0
        private val output = mutableListOf<Long>()
        var earlyBreak = false

        var a = inA
        var b = inB
        var c = inC

        fun reset() {
            ip = 0
            a = inA
            b = inB
            c = inC
            output.clear()
            earlyBreak = false
        }

        fun run(): List<Long> {
            while (ip < program.size && !earlyBreak) {
                run(program[ip], program[ip + 1])
                ip += 2
            }
            return output
        }

        private fun combo(operand: Long): Long = if (operand in 0..3) {
            operand
        } else if (operand == 4L) {
            a
        } else if (operand == 5L) {
            b
        } else if (operand == 6L) {
            c
        } else error("Wrong value of combo operand: $operand")

        fun run(opcode: Long, operand: Long) = when (opcode) {
            0L -> adv(operand)
            1L -> bxl(operand)
            2L -> bst(operand)
            3L -> jnz(operand)
            4L -> bxc()
            5L -> out(operand)
            6L -> bdv(operand)
            7L -> cdv(operand)
            else -> error("Unknown opcode: $opcode")
        }

        fun divByPowerOf2(reg: Long, operand: Long): Long {
            return reg / (1L shl combo(operand).toInt())
        }

        fun adv(operand: Long) {
            a = divByPowerOf2(a, operand)
        }

        fun bxl(operand: Long) {
            b = b xor operand
        }

        fun bst(operand: Long) {
            b = combo(operand) % 8
        }

        fun jnz(operand: Long) {
            if (a != 0L) {
                ip = (operand - 2L).toInt()
            }
        }

        fun bxc() {
            b = b xor c
        }

        fun out(operand: Long) {
            val outResult = combo(operand) % 8
            if (search) {
                if (output.size < program.size && program[output.size] == outResult) {
                    output.add(outResult)
                } else {
                    earlyBreak = true
                }
            } else {
                output.add(outResult)
            }
        }

        fun bdv(operand: Long) {
            b = divByPowerOf2(a, operand)
        }

        fun cdv(operand: Long) {
            c = divByPowerOf2(a, operand)
        }

    }

    fun parse(input: String, search: Boolean = false): Computer {
        val (ma, mb, mc, programStr) = "Register A: (\\d+)\nRegister B: (\\d+)\nRegister C: (\\d+)\n\nProgram: ((\\d,?)+)".toRegex().find(input)!!.groups.drop(1)
        val a = ma!!.value.toLong()
        val b = mb!!.value.toLong()
        val c = mc!!.value.toLong()
        val program = programStr!!.value.split(',').map { it.toLong() }
        return Computer(a, b, c, program, search)
    }

    fun part1(input: String): String {
        val computer = parse(input)
        val output = computer.run()
        return output.joinToString(",")
    }

    fun part2(input: String): Long {
        var start = System.currentTimeMillis()
        val iterationsToPrint = 1000000L
        val computer = parse(input, true)
        for (i in 1L..Long.MAX_VALUE) {
            if (i % iterationsToPrint == 0L) {
                val end = System.currentTimeMillis()
                println("Iteration $i, ${(iterationsToPrint * 1000L / (end - start))} iterations per second")
                start = end
            }
            computer.reset()
            computer.a = i
            val output = computer.run()
            if (!computer.earlyBreak && output == computer.program) {
                return i
            }
        }
        error("solution not found")
    }

}
