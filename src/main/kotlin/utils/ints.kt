package utils

fun Int.isEven() = this and 1 == 0

fun Int.isOdd() = this and 1 == 1

fun Int.nextIndexOf(list: List<*>) = (this + 1) % list.size
fun Int.prevIndexOf(list: List<*>) = (this + list.lastIndex) % list.size