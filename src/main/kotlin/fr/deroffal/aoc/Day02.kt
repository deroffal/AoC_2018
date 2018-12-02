package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files

fun main(args: Array<String>) {
    val input = Files.readAsList("day02.txt")
    println("Part 1 : ${Day02().part1(input)}")
    println("Part 2 : ${Day02().part2(input)}")
}

class Day02 {

    fun countAppearences(text: String) = text.toCharArray().toSet().map { char: Char -> text.filter { it == char }.count() }
    fun part1(input: List<String>): Int {
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
        val idsIterator = input.iterator()
        var pair: Pair<String, String>? = null
        while (idsIterator.hasNext() && pair == null) {
            val id1 = idsIterator.next()
            val id2 = input.filter { id1 != it }.find { idsMatching(id1, it) }
            if (id2 != null) pair = Pair(id1, id2)
        }
        return pair!!
    }

    fun part2(input: List<String>): String {
        val pairOfIds = findPairOfIds(input)
        return (pairOfIds.first zip pairOfIds.second).filter { it.first == it.second }.map { it.first }.joinToString(separator = "")
    }

}