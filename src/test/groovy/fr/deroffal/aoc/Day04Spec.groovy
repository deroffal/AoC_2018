package fr.deroffal.aoc


import spock.lang.Specification

import java.time.format.DateTimeFormatter

import static fr.deroffal.aoc.utils.Files.readAsList

class Day04Spec extends Specification {

    private static final DateTimeFormatter DATE_PATTERN_MM_DD = DateTimeFormatter.ofPattern('MM-dd')

    Day04 day04 = new Day04()

    void 'Parse input to Records'() {
        given:
        List<String> input = [
                '[1518-11-01 00:00] Guard #10 begins shift',
                '[1518-11-01 00:05] falls asleep',
                '[1518-11-01 00:25] wakes up',
                '[1518-11-01 00:30] falls asleep',
                '[1518-11-01 00:55] wakes up',
                '[1518-11-01 23:58] Guard #99 begins shift',
                '[1518-11-02 00:40] falls asleep',
                '[1518-11-02 00:50] wakes up',
                '[1518-11-03 00:05] Guard #10 begins shift',
                '[1518-11-03 00:24] falls asleep',
                '[1518-11-03 00:29] wakes up',
                '[1518-11-04 00:02] Guard #99 begins shift',
                '[1518-11-04 00:36] falls asleep',
                '[1518-11-04 00:46] wakes up',
                '[1518-11-05 00:03] Guard #99 begins shift',
                '[1518-11-05 00:45] falls asleep',
                '[1518-11-05 00:55] wakes up'
        ]
        when:
        List<Record> records = day04.parseToRecords(input)
        then:
        5 == records.size()
        Record record1 = records.first()
        10 == record1.id
        '11-01' == record1.date.format(DATE_PATTERN_MM_DD)
        record1.minutesAwaken.containsAll([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24])
        record1.minutesAwaken.containsAll([30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54])

        Record record2 = records[1]
        99 == record2.id
        '11-02' == record2.date.format(DATE_PATTERN_MM_DD)
        record2.minutesAwaken.containsAll([40, 41, 42, 43, 44, 45, 46, 47, 48, 49])

        Record record3 = records[2]
        10 == record3.id
        '11-03' == record3.date.format(DATE_PATTERN_MM_DD)
        record3.minutesAwaken.containsAll([24, 25, 26, 27, 28])

        Record record4 = records[3]
        99 == record4.id
        '11-04' == record4.date.format(DATE_PATTERN_MM_DD)
        record4.minutesAwaken.containsAll([36, 37, 38, 39, 40, 41, 42, 43, 44, 45])

        Record record5 = records[4]
        99 == record5.id
        '11-05' == record5.date.format(DATE_PATTERN_MM_DD)
        record5.minutesAwaken.containsAll([45, 46, 47, 48, 49, 50, 51, 52, 53, 54])
    }

    void 'Parse unsorted input to Records'() {
        given: 'The same input than the example, but unsorted'
        List<String> input = [
                '[1518-11-01 00:00] Guard #10 begins shift',
                '[1518-11-01 00:05] falls asleep',
                '[1518-11-05 00:45] falls asleep',
                '[1518-11-01 00:30] falls asleep',
                '[1518-11-04 00:36] falls asleep',
                '[1518-11-01 23:58] Guard #99 begins shift',
                '[1518-11-02 00:40] falls asleep',
                '[1518-11-02 00:50] wakes up',
                '[1518-11-03 00:05] Guard #10 begins shift',
                '[1518-11-03 00:24] falls asleep',
                '[1518-11-03 00:29] wakes up',
                '[1518-11-04 00:02] Guard #99 begins shift',
                '[1518-11-01 00:25] wakes up',
                '[1518-11-04 00:46] wakes up',
                '[1518-11-05 00:03] Guard #99 begins shift',
                '[1518-11-01 00:55] wakes up',
                '[1518-11-05 00:55] wakes up'
        ]
        when:
        List<Record> records = day04.parseToRecords(input)
        then: 'The same result is expected'
        5 == records.size()
        Record record1 = records.first()
        10 == record1.id
        '11-01' == record1.date.format(DATE_PATTERN_MM_DD)
        record1.minutesAwaken.containsAll([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24])
        record1.minutesAwaken.containsAll([30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54])

        Record record2 = records[1]
        99 == record2.id
        '11-02' == record2.date.format(DATE_PATTERN_MM_DD)
        record2.minutesAwaken.containsAll([40, 41, 42, 43, 44, 45, 46, 47, 48, 49])

        Record record3 = records[2]
        10 == record3.id
        '11-03' == record3.date.format(DATE_PATTERN_MM_DD)
        record3.minutesAwaken.containsAll([24, 25, 26, 27, 28])

        Record record4 = records[3]
        99 == record4.id
        '11-04' == record4.date.format(DATE_PATTERN_MM_DD)
        record4.minutesAwaken.containsAll([36, 37, 38, 39, 40, 41, 42, 43, 44, 45])

        Record record5 = records[4]
        99 == record5.id
        '11-05' == record5.date.format(DATE_PATTERN_MM_DD)
        record5.minutesAwaken.containsAll([45, 46, 47, 48, 49, 50, 51, 52, 53, 54])
    }

    void 'Apply strategy 1'() {
        given:
        List<String> input = [
                '[1518-11-01 00:00] Guard #10 begins shift',
                '[1518-11-01 00:05] falls asleep',
                '[1518-11-01 00:25] wakes up',
                '[1518-11-01 00:30] falls asleep',
                '[1518-11-01 00:55] wakes up',
                '[1518-11-01 23:58] Guard #99 begins shift',
                '[1518-11-02 00:40] falls asleep',
                '[1518-11-02 00:50] wakes up',
                '[1518-11-03 00:05] Guard #10 begins shift',
                '[1518-11-03 00:24] falls asleep',
                '[1518-11-03 00:29] wakes up',
                '[1518-11-04 00:02] Guard #99 begins shift',
                '[1518-11-04 00:36] falls asleep',
                '[1518-11-04 00:46] wakes up',
                '[1518-11-05 00:03] Guard #99 begins shift',
                '[1518-11-05 00:45] falls asleep',
                '[1518-11-05 00:55] wakes up'
        ]
        when:
        int result = day04.applyStrategy1(input)
        then:
        240 == result
    }

    void 'Apply strategy 2'() {
        given:
        List<String> input = [
                '[1518-11-01 00:00] Guard #10 begins shift',
                '[1518-11-01 00:05] falls asleep',
                '[1518-11-01 00:25] wakes up',
                '[1518-11-01 00:30] falls asleep',
                '[1518-11-01 00:55] wakes up',
                '[1518-11-01 23:58] Guard #99 begins shift',
                '[1518-11-02 00:40] falls asleep',
                '[1518-11-02 00:50] wakes up',
                '[1518-11-03 00:05] Guard #10 begins shift',
                '[1518-11-03 00:24] falls asleep',
                '[1518-11-03 00:29] wakes up',
                '[1518-11-04 00:02] Guard #99 begins shift',
                '[1518-11-04 00:36] falls asleep',
                '[1518-11-04 00:46] wakes up',
                '[1518-11-05 00:03] Guard #99 begins shift',
                '[1518-11-05 00:45] falls asleep',
                '[1518-11-05 00:55] wakes up'
        ]
        when:
        int result = day04.applyStrategy2(input)
        then:
        4455 == result
    }

    void 'Day04'() {
        given:
        List<String> input = readAsList('day04.txt')
        when: 'Part 1'
        int part1 = day04.applyStrategy1(input)
        then:
        part1 == 21083
        when: 'Part 2'
        int part2 = day04.applyStrategy2(input)
        then:
        part2 == 53024
    }
}
