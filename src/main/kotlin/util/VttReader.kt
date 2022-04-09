package util

import exception.InvalidFormatException
import model.Cues
import java.io.File

class VttReader(
    private val inputVtt: File
) {
    fun read(): Cues {
        val lines = inputVtt.readLines()

        if (lines.isEmpty()) {
            throw InvalidFormatException("Empty vtt file => ${inputVtt.absolutePath}")
        }

        if (lines[0].trim() != "WEBVTT") {
            throw InvalidFormatException("Invalid WEBVTT file => ${inputVtt.absolutePath}")
        }

        return Cues(listOf())
    }
}
