package utils

import java.util.*
import kotlin.math.min

/**
 * Convert up to 64 lower bits from the BitSet to a Long value.
 */
fun BitSet.toLong(): Long {
    var result = 0L
    (min(63, size() - 1) downTo 0).forEach { bit ->
        result = result shl 1
        result += if (this[bit]) 1L else 0L
    }
    return result
}
