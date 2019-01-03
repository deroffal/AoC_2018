package fr.deroffal.aoc.utils

import kotlin.Pair
import kotlin.ranges.IntRange
import spock.lang.Specification

class ExtensionsKtTest extends Specification {
    void "String.substringBetween"() {
        when:
        String stringBetween = ExtensionsKt.substringBetween('abcdefghijklmnopqrstuvwxyz', 'd', 'u')
        then:
        'efghijklmnopqrst' == stringBetween
    }

    void "Char.equalsIgnoreCase()"() {
        given:
        char self = a
        char other = b
        when:
        def result = ExtensionsKt.equalsIgnoreCase(self, other)
        then:
        expected == result
        where:
        a   | b   || expected
        'a' | 'b' || false
        'a' | 'B' || false
        'a' | 'A' || true
        'a' | 'a' || true
        'A' | 'a' || true
        'A' | 'A' || true
    }

    void 'IntRange.firstOrLast(value: Int)'() {
        given:
        IntRange intRange = new IntRange(-10, 10)
        when:
        boolean result = ExtensionsKt.firstOrLast(intRange, value)
        then:
        result == expected
        where:
        value || expected
        -100  || false
        -10   || true
        0     || false
        10    || true
        100   || false
    }

    void 'IntRange.times(other: IntRange)'() {
        given:
        IntRange xRange = new IntRange(-2, 1)
        IntRange yRange = new IntRange(1, 2)
        when:
        List<Pair> pairs = ExtensionsKt.times(xRange, yRange)
        then:
        pairs == [
                new Pair(-2, 1), new Pair(-2, 2),
                new Pair(-1, 1), new Pair(-1, 2),
                new Pair(0, 1), new Pair(0, 2),
                new Pair(1, 1), new Pair(1, 2)
        ]
    }
}
