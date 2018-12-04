package fr.deroffal.aoc

import spock.lang.Specification

import static fr.deroffal.aoc.utils.Files.readAsList

class Day03Spec extends Specification {

    Day03 day03 = new Day03()

    void 'String as Claim'() {
        given:
        String claim = '#1 @ 2,3: 4x5'
        when:
        Claim actualClaim = Claim.fromString(claim)
        then:
        actualClaim.id == 1
        actualClaim.xMin == 2
        actualClaim.xMax == 5
        actualClaim.yMin == 3
        actualClaim.yMax == 7
    }

    void 'Is a coordinate in a claim ?'() {
        given:
        SquareInch coordinate = new SquareInch(x, y)
        Claim claim = new Claim(1, xMin, xMax, yMin, yMax)
        when:
        boolean isCoordinateInClaim = coordinate.isInClaim(claim)
        then:
        isCoordinateInClaim == expected
        where:
        x | y | xMin | xMax | yMin | yMax || expected
        0 | 0 | 1    | 1    | 1    | 1    || false
        1 | 1 | 1    | 1    | 1    | 1    || true
        3 | 5 | 2    | 4    | 4    | 6    || true
    }

    void 'Count square inches in at least 2 claims'() {
        when:
        int result = day03.countSquareInchesInAtLeastTwoClaims(input, xMax, yMax)
        then:
        result == expected
        where:
        i                  | input                                               | xMax | yMax || expected
        'only one'         | ['#1 @ 0,0: 1x1']                                   | 1    | 1    || 0
        'twice same claim' | ['#1 @ 0,0: 1x1', '#2 @ 0,0: 1x1']                  | 1    | 1    || 1
        '3x3 center'       | ['#1 @ 1,1: 1x1', '#2 @ 1,1: 1x1']                  | 3    | 3    || 1
        'Example'          | ['#1 @ 1,3: 4x4', '#2 @ 3,1: 4x4', '#3 @ 5,5: 2x2'] | 11   | 6    || 4
    }

    void 'Find the only claims not overlaped by any other'() {
        when:
        Integer claimId = day03.findClaimNotOverlaped(['#1 @ 1,3: 4x4', '#2 @ 3,1: 4x4', '#3 @ 5,5: 2x2'], 11, 9)
        then:
        3 == claimId
    }

    void 'Day03'() {
        given: 'Input day 03'
        List<String> input = readAsList("day03.txt")
        when: 'Part 1'
        Integer part1 = day03.countSquareInchesInAtLeastTwoClaims(input, 1000, 1000)
        then:
        114946 == part1
        when: 'Part 2'
        Integer part2 = day03.findClaimNotOverlaped(input, 1000, 1000)
        then:
        877 == part2
    }
}
