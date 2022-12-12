package utils

/**
 * The index of the character in the sequence abc...zABC...Z
 */
val Char.letterIndex: Int
    get() = when (this) {
        in ('a'..'z') -> this - 'a'
        in ('A'..'Z') -> 26 + (this - 'A')
        else -> error("Only works for latin letters!")
    }
