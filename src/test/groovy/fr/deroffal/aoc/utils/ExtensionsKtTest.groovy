package fr.deroffal.aoc.utils

import spock.lang.Specification
import spock.lang.Unroll

class ExtensionsKtTest extends Specification {
    def "String.substringBetween"() {
        when:
        String stringBetween = ExtensionsKt.substringBetween('abcdefghijklmnopqrstuvwxyz', 'd', 'u')
        then:
        'efghijklmnopqrst' == stringBetween
    }

    def "Char.equalsIgnoreCase()"() {
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
}
