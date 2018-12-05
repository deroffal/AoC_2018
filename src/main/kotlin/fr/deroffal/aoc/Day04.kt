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
        var minutes = 0

        input.sorted()
                .forEach {
                    when {
                        it.contains("#") -> {
                            record = parseBeginShiftsLineToRecord(it)
                            records.add(record!!)
                        }
                        it.contains("falls asleep") -> minutes = parseTime(it).minute
                        else -> record?.minutesAwaken?.addAll(minutes until parseTime(it).minute)

                    }
                }
        return records
    }

    private fun parseBeginShiftsLineToRecord(line: String): Record {
        val date = if (parseTime(line).hour == 0) parseDate(line) else parseDate(line).plusDays(1)
        return Record(date, line.substringBetween("#", " ").toInt())
    }

    private fun parseTime(line: String) = LocalTime.parse(line.substringBetween(" ", "]"))
    private fun parseDate(line: String) = LocalDate.parse(line.substringBetween("[", " "))

    fun applyStrategy1(input: List<String>): Int {
        val recordsByGuard = parseToRecords(input).groupBy { it.id }

        val laziestGuardId =
                recordsByGuard.maxBy {
                    it.value.map { record -> record.minutesAwaken }.flatten().count()
                }!!.key

        val minute =
                recordsByGuard[laziestGuardId]!!
                        .map { it.minutesAwaken }.flatten()//list of all (duplicated) minutes asleep
                        .groupBy { it } //group each minute with all the same minutes
                        .maxBy { it.value.size }!!.key // find the minute when he's the most often asleep


        return laziestGuardId * minute
    }

    fun applyStrategy2(input: List<String>): Int {
        val recordsByGuard = parseToRecords(input).groupBy { it.id }
        val occurencesOfMinuteAsleepByGuard = recordsByGuard
                .mapValues { map ->
                    map.value.map { it.minutesAwaken }.flatten()//map each record to all of its minutes
                            .groupBy { it }.mapValues { it.value.size }//value = [minute:count]
                }.filter { !it.value.isEmpty() } //no need guars who doesn't sleep

        val first = occurencesOfMinuteAsleepByGuard
                .mapValues { map -> map.value.maxBy { it.value }!! }
                .maxBy { it.value.value }!!

        return first.key * first.value.key
    }
}

