package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsList
import fr.deroffal.aoc.utils.repeat

fun main(args: Array<String>) {
    val input = readAsList("day01.txt")
    println("Part 1 : ${Day01().computeResultingFrequency(input)}")
    println("Part 2 : ${Day01().findFirstFrequencyRepeatedTwice(input)}")
}

class Day01 {
    fun computeResultingFrequency(input: List<String>): Int = input.map { it.toInt() }.sum()

    fun findFirstFrequencyRepeatedTwice(input: List<String>): Int {
        var currentFrequency = 0
        val frequencies = mutableSetOf(currentFrequency)
        input.map { it.toInt() }.repeat().forEach {
            currentFrequency += it
            if (!frequencies.contains(currentFrequency)) {
                frequencies.add(currentFrequency)
            } else {
                return currentFrequency
            }
        }
        throw Exception()
    }
}