package utils

fun <T> Set<T>.powerSetSequence() = (0..<(1 shl size)).asSequence()
    .map { setDescriptor ->
        filterIndexed { idx, _ -> (setDescriptor shr idx).isOdd() }.toSet()
    }
