package utils

enum class Op(
    val char: Char,
    val operation: (Long, Long) -> Long,
    val leftReverse: (Long, Long) -> Long,
    val rightReverse: (Long, Long) -> Long,
    val identity: Long
) {
    ADD('+', { a, b -> a + b }, { a, b -> a - b }, { a, b -> a - b }, 0L),
    SUB('-', { a, b -> a - b }, { a, b -> a + b }, { a, b -> b - a }, 0L),
    DIV('/', { a, b -> a / b }, { a, b -> a * b }, { a, b -> b / a }, 1L),
    MUL('*', { a, b -> a * b }, { a, b -> a / b }, { a, b -> a / b }, 1L);

    companion object {
        fun fromChar(char: Char): Op = entries.find { it.char == char }!!
    }
}
