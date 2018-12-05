package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsString
import fr.deroffal.aoc.utils.equalsIgnoreCase

fun main(args: Array<String>) {
    val input = readAsString("day05.txt")
    println("Part 1 : ${Day05().reducePolymere(input)}")
    println("Part 2 : ${Day05().findUnitCausingProblems(input)}")
}

class Day05 {

    fun reducePolymere(polymere: String): Int {
        var remainingPolymere = polymere
        var reactingUnits = remainingPolymere.zipWithNext().filter { it.first.sameTypeAndOppositePolarity(it.second) }
        while (!reactingUnits.isEmpty()) {
            reactingUnits.forEach {
                remainingPolymere = remainingPolymere.replace(it.asString(), "")
            }
            reactingUnits = remainingPolymere.zipWithNext().filter { it.first.sameTypeAndOppositePolarity(it.second) }
        }
        return remainingPolymere.length
    }

    fun findUnitCausingProblems(polymere: String): Int {
        return ('a'..'z').map {
            polymere.replace(it.toString(), "").replace(it.toString().toUpperCase(), "")
        }.map { reducePolymere(it) }.min()!!
    }

    private fun Char.sameTypeAndOppositePolarity(other: Char) = this.equalsIgnoreCase(other) && (this.isLowerCase() != other.isLowerCase())
    private fun Pair<Char, Char>.asString() = "${this.first}${this.second}"
    private fun Pair<Char, Char>.reverse() = Pair(this.second, this.first)
}