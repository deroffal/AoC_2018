package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsList
import fr.deroffal.aoc.utils.firstOrLast
import fr.deroffal.aoc.utils.times
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = readAsList("day06.txt")
    println("Part 1 : ${Day06().findLargestNotInfinitArea(input)}")
}

data class Coordinate(val x: Int, val y: Int) {
    companion object {
        @JvmStatic
        fun parseString(string: String) = string.split(", ")
                .map { it.toInt() }
                .run { Coordinate(this[0], this[1]) }
    }

    fun distanceTo(coordinate: Coordinate) = abs(this.x - coordinate.x) + abs(this.y - coordinate.y)

    // assuming x & y are in (inclusive) the ranges
    fun isOnOneEdge(xRange: IntRange, yRange: IntRange) = xRange.firstOrLast(this.x) || yRange.firstOrLast(this.y)
}

class Day06 {
    fun findLargestNotInfinitArea(input: List<String>): Int {
        val coordinates = input.map { Coordinate.parseString(it) }
        val xRange = (coordinates.minBy { it.x }!!.x..coordinates.maxBy { it.x }!!.x)
        val yRange = (coordinates.minBy { it.y }!!.y..coordinates.maxBy { it.y }!!.y)


        val pointsWithInfiniteArea: MutableSet<Coordinate> = mutableSetOf()
        val points = (xRange * yRange).map { Coordinate(it.first, it.second) }
        val closestCoordinate = points.map { point ->
            val twoClosest = coordinates.map { it to it.distanceTo(point) }
                    .sortedBy { it.second }
                    .take(2)

            if (point.isOnOneEdge(xRange, yRange)) {
                pointsWithInfiniteArea.add(twoClosest[0].first)
            }
            twoClosest[0].first.takeUnless { twoClosest[0].second == twoClosest[1].second }
        }

        return closestCoordinate
                .filterNot { it in pointsWithInfiniteArea }
                .groupingBy { it }.eachCount()
                .maxBy { it.value }!!.value
    }
}