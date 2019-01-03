package fr.deroffal.aoc

import spock.lang.Specification

import static fr.deroffal.aoc.utils.Files.readAsString

class Day05Spec extends Specification {

    Day05 day05 = new Day05()

    void 'Char.sameTypeAndOppositePolarity'() {
        given:
        char self = a
        char other = b
        when:
        def result = day05.sameTypeAndOppositePolarity(self, other)
        then:
        result == expected
        where:
        a   | b   || expected
        'a' | 'b' || false
        'a' | 'B' || false
        'a' | 'A' || true
        'a' | 'a' || false
        'A' | 'a' || true
        'A' | 'A' || false
    }

    void 'Reduce polymere'() {
        when:
        int reducedPolymereLength = day05.reducePolymere('dabAcCaCBAcCcaDA')
        then:
        reducedPolymereLength == 'dabCBAcaDA'.length()
    }

    void 'Find unit causing problems'() {
        when:
        int reducedPolymereLength = day05.findUnitCausingProblems('dabAcCaCBAcCcaDA')
        then:
        reducedPolymereLength == 4
    }

    void 'Day05'() {
        given:
        String input = readAsString('day05.txt')
        when: 'Part 1'
        int part1 = day05.reducePolymere(input)
        then:
        part1 == 9238
        when: 'Part 2'
        int part2 = day05.findUnitCausingProblems(input)
        then:
        part2 == 4052
    }
}
