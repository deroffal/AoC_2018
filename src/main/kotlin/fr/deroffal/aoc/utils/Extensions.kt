package fr.deroffal.aoc.utils

//https://stackoverflow.com/a/48007581
fun <T> List<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

fun String.substringBetween(after: String, before: String) = this.substringAfter(after).substringBefore(before)

fun Char.equalsIgnoreCase(other: Char) = this.toLowerCase() == other.toLowerCase()