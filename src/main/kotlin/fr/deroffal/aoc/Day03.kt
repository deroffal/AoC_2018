package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsList
import fr.deroffal.aoc.utils.substringBetween
import fr.deroffal.aoc.utils.times

fun main(args: Array<String>) {
    val input = readAsList("day03.txt")
    println("Part 1 : ${Day03().countSquareInchesInAtLeastTwoClaims(input, 1000, 1000)}")
    println("Part 2 : ${Day03().findClaimNotOverlaped(input, 1000, 1000)}")
}

data class Claim(val id: Int, val xMin: Int, val xMax: Int, val yMin: Int, val yMax: Int) {
    companion object {
        @JvmStatic
        fun fromString(claim: String): Claim {
            return Claim(claim.substringBetween("#", " @").toInt(),
                    claim.substringBetween("@ ", ",").toInt(),
                    claim.substringBetween("@ ", ",").toInt() + claim.substringBetween(": ", "x").toInt() - 1,
                    claim.substringBetween(",", ":").toInt(),
                    claim.substringBetween(",", ":").toInt() + claim.substringAfter("x").toInt() - 1
            )
        }
    }

}

data class SquareInch(val x: Int, val y: Int) {
    fun isInClaim(claim: Claim): Boolean = this.x in claim.xMin..claim.xMax && this.y in claim.yMin..claim.yMax

}

class Day03 {

    fun countSquareInchesInAtLeastTwoClaims(input: List<String>, xMax: Int, yMax: Int): Int = findSquareWithAtLeastTwoClaims(yMax, xMax, input).count()

    fun findClaimNotOverlaped(input: List<String>, xMax: Int, yMax: Int): Int {
        val squareWithAtLeastTwoClaims = findSquareWithAtLeastTwoClaims(yMax, xMax, input)
        return input.map { Claim.fromString(it) }.find { claim -> !squareWithAtLeastTwoClaims.any { it.isInClaim(claim) } }?.id!!
    }

    private fun findSquareWithAtLeastTwoClaims(yMax: Int, xMax: Int, input: List<String>): List<SquareInch> {
        val squareInches: List<SquareInch> = ((0 until yMax) * (0 until xMax)).map { SquareInch(it.second, it.first) }
        val claims = input.map { Claim.fromString(it) }
        return squareInches.filter { claims.filter { claim -> it.isInClaim(claim) }.count() >= 2 }
    }
}