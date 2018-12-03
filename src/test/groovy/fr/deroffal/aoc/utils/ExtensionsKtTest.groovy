package fr.deroffal.aoc.utils

import spock.lang.Specification

class ExtensionsKtTest extends Specification {
    def "SubstringBetween"() {
        when:
        String stringBetween = ExtensionsKt.substringBetween('abcdefghijklmnopqrstuvwxyz', 'd', 'u')
        then:
        'efghijklmnopqrst' == stringBetween
    }
}
