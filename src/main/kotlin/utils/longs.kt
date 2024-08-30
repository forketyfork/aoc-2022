package utils

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun Long.gcd(other: Long): Long {
    return if (this == 0L || other == 0L) {
        this + other
    } else {
        val absNumber1 = abs(this)
        val absNumber2 = abs(other)
        val biggerValue = max(absNumber1, absNumber2)
        val smallerValue = min(absNumber1, absNumber2)
        (biggerValue % smallerValue).gcd(smallerValue)
    }
}

fun Long.lcm(other: Long) = if (this == 0L || other == 0L) 0L else abs(this * other) / this.gcd(other)
