package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files

fun main(args: Array<String>) {
    val input = Files.readAsList("day02.txt")
    println("Part 1 : ${Day02().computeChecksum(input)}")
    println("Part 2 : ${Day02().findCommonLettersInCorrectIds(input)}")
}

class Day02 {

    fun computeChecksum(input: List<String>) =
            input.map { countAppearences(it) }
                    .run {
                        this.filter { it.contains(2) }.count() * this.filter { it.contains(3) }.count()
                    }

    private fun countAppearences(text: String) = text.toCharArray()
            .groupBy { it }.mapValues { it.value.size } //[char: listOfThisChar]
            .entries.groupBy({ it.value }, { it.key })  //[count: listOfEachChar]
            .keys                                       //[actual counts]

    //only one different char over the 2 ids
    private fun idsMatching(id1: String, id2: String) =
            (id1 zip id2).filter { it.first == it.second }.size == id1.length - 1

    private fun findPairOfIds(input: List<String>): Pair<String, String> = input
            .map {
                it to (
                        input.filter { id -> it != id }
                                .find { id -> idsMatching(it, id) })
                        .orEmpty()
            }
            .first { it.second != "" }


    fun findCommonLettersInCorrectIds(input: List<String>) =
            findPairOfIds(input).run {
                (this.first zip this.second)
                        .filter { it.first == it.second }
                        .map { it.first }.joinToString(separator = "")
            }

}