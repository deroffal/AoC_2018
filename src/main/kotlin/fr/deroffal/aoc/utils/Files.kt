package fr.deroffal.aoc.utils

import java.io.File
import java.io.FileNotFoundException

/**
 * @JvmStatic allows to use functions as static in groovy
 * @JvmOverloads allows to use functions without optional parameters in groovy
 *
 * @see <a href="https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html">Calling Kotlin from Java (or Groovy)</a>
 */

internal object Files {

    @JvmStatic
    fun readAsString(fileName: String): String = ClassLoader.getSystemResource(fileName).readText()

    @JvmStatic
    fun readAsList(fileName: String) = File(ClassLoader.getSystemResource(fileName).toURI()).readLines()

    @JvmStatic
    fun readAndSplit(fileName: String, delimiter: String) = readAsString(fileName).split(delimiter)

}