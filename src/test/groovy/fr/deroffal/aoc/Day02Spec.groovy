package fr.deroffal.aoc

import kotlin.Pair
import spock.lang.Specification

class Day02Spec extends Specification {

    Day02 day02 = new Day02()

    def "GroupByCountOfAppearance"() {
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

    def 'part1'() {
        when:
        def score = day02.part1(['abcdef', 'bababc', 'abbcde', 'abcccd', 'aabcdd', 'abcdee', 'ababab'])
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

    def 'part2'() {
        when:
        def result = day02.part2(['abcde', 'fghij', 'klmno', 'pqrst', 'fguij', 'axcye', 'wvxyz'])
        then:
        result == 'fgij'
    }
}
