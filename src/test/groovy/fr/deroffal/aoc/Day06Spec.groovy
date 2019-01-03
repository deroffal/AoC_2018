package fr.deroffal.aoc

import kotlin.ranges.IntRange
import spock.lang.Specification

import static fr.deroffal.aoc.utils.Files.readAsList

class Day06Spec extends Specification {

    Day06 day06 = new Day06()

    void 'String to Coordinate'() {
        given:
        String string = '8, 3'
        when:
        Coordinate coordinate = Coordinate.parseString(string)
        then:
        coordinate.x == 8
        coordinate.y == 3
    }

    void 'Distance between 2 Coordinates'() {
        given:
        Coordinate coordinate1 = new Coordinate(-5, -9)
        Coordinate coordinate2 = new Coordinate(12, 18)
        when:
        int distance = coordinate1.distanceTo(coordinate2)
        then:
        distance == 44
    }

    void 'Is a coordinate on an edge ?'() {
        given:
        IntRange xRange = new IntRange(-5, 10)
        IntRange yRange = new IntRange(2, 7)
        when:
        boolean result = new Coordinate(x, y).isOnOneEdge(xRange, yRange)
        then:
        result == expected
        where:
        x   | y   || expected
        -59 | 37  || false
        -5  | -20 || true
        2   | 18  || false
        10  | -8  || true
        15  | 18  || false

        -8  | -9  || false
        12  | 2   || true
        18  | 5   || false
        37  | 7   || true
        -25 | 18  || false


        -5  | 7   || true
        10  | 2   || true
    }

    void 'Part 1 - Example'() {
        given:
        List<String> input = ['1, 1', '1, 6', '8, 3', '3, 4', '5, 5', '8, 9']
        when:
        int areaSize = day06.findLargestNotInfinitArea(input)
        then:
        areaSize == 17
    }

    void 'Day06'() {
        given:
        List<String> input = readAsList('day06.txt')
        when: 'Part 1'
        int part1 = day06.findLargestNotInfinitArea(input)
        then:
        part1 == 3882
    }
}
