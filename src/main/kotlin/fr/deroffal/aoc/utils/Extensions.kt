package fr.deroffal.aoc.utils

//https://stackoverflow.com/a/48007581
fun <T> List<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }