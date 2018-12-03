package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files
import spock.lang.Specification

class Day02Spec extends Specification {

    Day02 day02 = new Day02()

    void "GroupByCountOfAppearance"() {
        when:
        List<Integer> list = day02.countAppearences(text)
        then:
        hasAnyLetterAppearingTwice == list.contains(2)
        hasAnyLetterAppearingThreeTimes == list.contains(3)
        where:
        text     || hasAnyLetterAppearingTwice || hasAnyLetterAppearingThreeTimes
        'abcdef' || false                      || false
        'bababc' || true                       || true
        'abbcde' || true                       || false
        'abcccd' || false                      || true
        'aabcdd' || true                       || false
        'abcdee' || true                       || false
        'ababab' || false                      || true

    }

    def 'Checkshum should match example'() {
        when:
        def score = day02.computeChecksum(['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'])
        then:
        score == 12
    }

    def 'Return true if only differing by 1 character'() {
        when:
        boolean result = day02.idsMatching(id1, id2)
        then:
        result == expected
        where:
        id1     | id2     || expected
        'abcde' | 'axcye' || false
        'fghij' | 'fguij' || true
    }

    def 'Find pair of ids'() {
        when:
        def ids = day02.findPairOfIds(['abcde', 'fghij', 'klmno', 'pqrst', 'fguij', 'axcye', 'wvxyz'])
        then:
        ids.first == 'fghij'
        ids.second == 'fguij'
    }

    def 'part2 - example'() {
        when:
        def result = day02.findCommonLettersInCorrectIds(['abcde', 'fghij', 'klmno', 'pqrst', 'fguij', 'axcye', 'wvxyz'])
        then:
        result == 'fgij'
    }

    void 'Day02'() {
        given: 'Input day 02'
        List<String> input = Files.readAsList("day02.txt")
        when: 'Part 1'
        Integer part1 = day02.computeChecksum(input)
        then:
        7872 == part1
        when: 'Part 2'
        String part2 = day02.findCommonLettersInCorrectIds(input)
        then:
        'tjxmoewpdkyaihvrndfluwbzc' == part2
    }
}
