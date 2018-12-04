package fr.deroffal.aoc

import fr.deroffal.aoc.utils.Files.readAsList
import fr.deroffal.aoc.utils.substringBetween
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val input = readAsList("day04.txt")
    println("Part 1 : ${Day04().applyStrategy1(input)}")
    println("Part 2 : ${Day04().applyStrategy2(input)}")
}

data class Record(val date: LocalDate, val id: Int, val minutesAwaken: MutableList<Int> = mutableListOf())

class Day04 {
    fun parseToRecords(input: List<String>): List<Record> {
        val records: MutableList<Record> = mutableListOf()

        var record: Record? = null
        var time = 0

        input.sortedBy { it }
                .forEach {
                    when {
                        it.contains("#") -> {
                            record = parseBeginShiftsLineToRecord(it)
                            records.add(record!!)
                        }
                        it.contains("falls asleep") -> time = parseTime(it).minute
                        it.contains("wakes up") -> {
                            val wakeUpMinute = parseTime(it).minute
                            record?.minutesAwaken?.addAll(time until wakeUpMinute)
                        }
                        else -> throw IllegalArgumentException()
                    }
                }
        return records
    }

    private fun parseBeginShiftsLineToRecord(line: String): Record {
        val date = if (parseTime(line).hour == 0) parseDate(line) else parseDate(line).plusDays(1)
        return Record(date, line.substringBetween("#", " ").toInt())
    }

    private fun parseTime(line: String) = LocalTime.parse(line.substringBetween(" ", "]"))
    private fun parseDate(line: String) = LocalDate.parse(line.substringBetween("[", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun applyStrategy1(input: List<String>): Int {
        val records = parseToRecords(input)

        val laziestGuardId =
                records.groupBy { it.id } //records by guards
                        .mapValues { map -> map.value.map { it.minutesAwaken }.flatten().count() } //map each record to the sum of its minutes
                        .entries.sortedByDescending { it.value }.first().key //sort and find who's sleeping the most

        val minute =
                records.filter { it.id == laziestGuardId }//laziest guard's record
                        .map { it.minutesAwaken }.flatten()//list of all (duplicated) minutes asleep
                        .groupBy { it }.mapValues { it.value.size }// map [minute:count]
                        .entries.sortedByDescending { it.value }.first().key // find the minute when he's the most often asleep


        return laziestGuardId * minute
    }

    fun applyStrategy2(input: List<String>): Int {
        val records = parseToRecords(input)
        val occurencesOfMinuteAsleepByGuard = records.groupBy { it.id } //records by guards
                .mapValues { map ->
                    map.value.map { it.minutesAwaken }.flatten()//map each record to all of its minutes
                            .groupBy { it }.mapValues { it.value.size }//value = [minute:count]
                }.filter { !it.value.isEmpty() } //no need guars who doesn't sleep

        val first = occurencesOfMinuteAsleepByGuard
                .mapValues { map ->
                    map.value.entries.sortedByDescending { it.value }.first()
                }.entries.sortedByDescending { it.value.value }.first()

        return first.key * first.value.key
    }
}