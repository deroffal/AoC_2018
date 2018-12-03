package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files
import spock.lang.Specification

class Day01Spec extends Specification {

    Day01 day01 = new Day01()

    void 'Compute reulting frequency'() {
        when:
        int frequency = day01.computeResultingFrequency(input)
        then:
        expectedResult == frequency
        where:
        input                    || expectedResult
        ['+1', '-2', '+3', '+1'] || 3
        ['+1', '+1', '+1']       || 3
        ['+1', '+1', '-2']       || 0
        ['-1', '-2', '-3']       || -6
    }

    void 'Find first frequency repeated twice'() {
        when:
        int frequency = day01.findFirstFrequencyRepeatedTwice(input)
        then:
        expectedResult == frequency
        where:
        input                          || expectedResult
        ['+1', '-1']                   || 0
        ['+1', '-2', '+3', '+1']       || 2
        ['+3', '+3', '+4', '-2', '-4'] || 10
        ['-6', '+3', '+8', '+5', '-6'] || 5
        ['+7', '+7', '-2', '-7', '-4'] || 14
    }

    void 'Day01'() {
        given: 'Input day 01'
        List<String> input = Files.readAsList("day01.txt")
        when: 'Part 1'
        Integer part1 = day01.computeResultingFrequency(input)
        then:
        582 == part1
        when: 'Part 2'
        Integer part2 = day01.findFirstFrequencyRepeatedTwice(input)
        then:
        488 == part2
    }
}
