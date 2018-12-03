package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files

fun main(args: Array<String>) {
    val input = Files.readAsList("day02.txt")
    println("Part 1 : ${Day02().computeChecksum(input)}")
    println("Part 2 : ${Day02().findCommonLettersInCorrectIds(input)}")
}

class Day02 {

    fun countAppearences(text: String) = text.toCharArray().toSet().map { char: Char -> text.filter { it == char }.count() }
    fun computeChecksum(input: List<String>): Int {
        var score2 = 0
        var score3 = 0
        input.forEach {
            val appearences = countAppearences(it)
            if (appearences.contains(2)) score2++
            if (appearences.contains(3)) score3++
        }
        return score2 * score3
    }

    //only one different char over the 2 ids
    private fun idsMatching(id1: String, id2: String) = (id1 zip id2).filter { it.first == it.second }.size == id1.length - 1

    private fun findPairOfIds(input: List<String>): Pair<String, String> {
        input.forEach {
            val id2Candidate = input.filter { id -> it != id }.find { id -> idsMatching(it, id) }
            if (id2Candidate != null) return Pair(it, id2Candidate)
        }
        throw Exception()
    }

    fun findCommonLettersInCorrectIds(input: List<String>): String {
        val pairOfIds = findPairOfIds(input)
        return (pairOfIds.first zip pairOfIds.second).filter { it.first == it.second }.map { it.first }.joinToString(separator = "")
    }

}