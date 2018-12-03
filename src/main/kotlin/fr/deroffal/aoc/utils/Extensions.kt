package fr.deroffal.aoc.utils

fun String.substringBetween(after: String, before: String) = this.substringAfter(after).substringBefore(before)