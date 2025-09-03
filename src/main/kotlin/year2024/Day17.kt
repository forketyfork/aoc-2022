package year2024

import utils.*
import java.util.*
import kotlin.math.*

class Day17 {

    class Computer(val inA: Long, val inB: Long, val inC: Long, val program: List<Long>) {

        private var ip = 0
        private val output = mutableListOf<Long>()

        var a = inA
        var b = inB
        var c = inC

        fun reset() {
            ip = 0
            a = inA
            b = inB
            c = inC
            output.clear()
        }

        fun run(): List<Long> {
            while (ip < program.size) {
                run(program[ip], program[ip + 1])
                ip += 2
            }
            return output
        }

        private fun combo(operand: Long): Long = when (operand) {
            in 0..3 -> operand
            4L -> a
            5L -> b
            6L -> c
            else -> error("Wrong value of combo operand: $operand")
        }

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

        fun adv(operand: Long) {
            a = a shr combo(operand).toInt()
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
            output.add(outResult)
        }

        fun bdv(operand: Long) {
            b = a shr combo(operand).toInt()
        }

        fun cdv(operand: Long) {
            c = a shr combo(operand).toInt()
        }

    }

    fun parse(input: String): Computer {
        val (ma, mb, mc, programStr) = "Register A: (\\d+)\nRegister B: (\\d+)\nRegister C: (\\d+)\n\nProgram: ((\\d,?)+)".toRegex()
            .find(input)!!.groups.drop(1)
        val a = ma!!.value.toLong()
        val b = mb!!.value.toLong()
        val c = mc!!.value.toLong()
        val program = programStr!!.value.split(',').map { it.toLong() }
        return Computer(a, b, c, program)
    }

    fun part1(input: String) = parse(input).run().joinToString(",")

    fun part2(input: String): Long {
        val computer = parse(input)
        val result = arrayOf(Long.MAX_VALUE)
        solvePart2(computer, result)
        return result.minOrNull() ?: error("Not found")
    }

    fun solvePart2(
        computer: Computer,
        minResult: Array<Long>,
        idx: Int = 0,
        bits: BitSet = BitSet(64),
        calculatedBits: BitSet = BitSet(64),
    ): Boolean {

        // Program: 2,4,1,5,7,5,1,6,0,3,4,2,5,5,3,0
        // bst 4 => b := a % 8      // store the last digit of `a` in `b`
        // bxl 5 => b := b xor 5    // xor it with 5 (bits 101)
        // cdv 5 => c := a shr b    // shift a right by b and put the result into c
        // bxl 6 => b := b xor 6    // xor b by 6 (bits 110)
        // adv 3 => a := a shr 3    // shift a right by 3
        // bxs 2 -> b := b xor c    // xor b with c
        // out b ->                 // output b % 8
        // jnz 0 => if (a != 0) jmp // repeat

        if (idx == computer.program.size) {
            minResult[0] = min(minResult[0], bits.toLong())
            return true
        }

        val startBit = idx * 3
        for (lowerPart in 0..7) {
            val shift = lowerPart xor 5
            val higherStartBit = startBit + shift
            for (higherPart in 0..7) {
                bits.setFrom(calculatedBits, startBit, lowerPart)
                bits.setFrom(calculatedBits, higherStartBit, higherPart)

                computer.reset()
                computer.a = bits.toLong()
                val output = computer.run()

                if (output.take(idx + 1) == computer.program.take(idx + 1)) {
                    calculatedBits.set(startBit, startBit + 3)
                    calculatedBits.set(higherStartBit, higherStartBit + 3)
                    if (solvePart2(computer, minResult, idx + 1, bits, calculatedBits)) {
                        return true
                    }
                } else {
                    calculatedBits.clear(startBit, startBit + 3)
                    calculatedBits.clear(higherStartBit, higherStartBit + 3)
                }
            }
        }
        return false
    }

}

private fun BitSet.setFrom(mask: BitSet, startBit: Int, octalValue: Int) {
    if (!mask[startBit]) set(startBit, octalValue and 1 == 1)
    if (!mask[startBit + 1]) set(startBit + 1, octalValue and 2 == 2)
    if (!mask[startBit + 2]) set(startBit + 2, octalValue and 4 == 4)
}
