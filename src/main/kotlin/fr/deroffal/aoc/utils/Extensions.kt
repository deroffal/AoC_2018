package fr.deroffal.aoc.utils

//https://stackoverflow.com/a/48007581
fun <T> List<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

fun String.substringBetween(after: String, before: String) = this.substringAfter(after).substringBefore(before)

fun Char.equalsIgnoreCase(other: Char) = this.toLowerCase() == other.toLowerCase()

fun IntRange.firstOrLast(value: Int) = value == this.first || value == this.last

/** @see <a href="https://kotlinlang.org/docs/reference/operator-overloading.html">Operator overloading</a> */
operator fun IntRange.times(other: IntRange) = this.flatMap { x -> other.map { y -> Pair(x, y) } }

