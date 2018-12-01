package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsList

fun main(args: Array<String>) {
    val input = readAsList("day01.txt")
    println("Part 1 : ${Day01().computeResultingFrequency(input)}")
    println("Part 2 : ${Day01().findFirstFrequencyRepeatedTwice(input)}")
}

class Day01 {
    fun computeResultingFrequency(input: List<String>): Int = input.map { it.toInt() }.sum()

    //TODO https://stackoverflow.com/questions/48007311/how-do-i-infinitely-repeat-a-sequence-in-kotlin
    fun findFirstFrequencyRepeatedTwice(input: List<String>): Int {
        var currentFrequency = 0
        val frequencies = mutableSetOf(currentFrequency)

        while (true) {
            input.forEach {
                currentFrequency += it.toInt()
                if (!frequencies.contains(currentFrequency)) {
                    frequencies.add(currentFrequency)
                } else {
                    return currentFrequency
                }
            }
        }
    }
}