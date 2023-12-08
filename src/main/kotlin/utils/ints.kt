package utils

import kotlin.math.*

fun Int.isEven() = this and 1 == 0

fun Int.isOdd() = this and 1 == 1

fun Int.nextIndexOf(list: List<*>) = (this + 1) % list.size
fun Int.prevIndexOf(list: List<*>) = (this + list.lastIndex) % list.size

fun Int.gcd(other: Int): Int {
    return if (this == 0 || other == 0) {
        this + other
    } else {
        val absNumber1 = abs(this)
        val absNumber2 = abs(other)
        val biggerValue = max(absNumber1, absNumber2)
        val smallerValue = min(absNumber1, absNumber2)
        (biggerValue % smallerValue).gcd(smallerValue)
    }
}

fun Int.lcm(other: Int) = if (this == 0 || other == 0) 0 else abs(this * other) / this.gcd(other)

