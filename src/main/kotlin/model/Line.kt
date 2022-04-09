package model

import exception.InvalidLineSeparatorException

data class Line(
    val text: String
) {
    private val line = text.trim()

    companion object {
        private val newLine = System.getProperty("line.separator")
    }

    fun getType(): LineType {
        if (line.contains(newLine)) {
            throw InvalidLineSeparatorException("each line should not contain a new line character")
        }

        if (line.isBlank()) {
            return LineType.EMPTY
        }

        if (line == "WEBVTT") {
            return LineType.HEADER
        }

        if (line == "STYLE") {
            return LineType.STYLE
        }

        if (line == "NOTE") {
            return LineType.NOTE
        }

        if (line == "REGION") {
            return LineType.REGION
        }

        if (line.contains("-->")) {
            return LineType.POSITION
        }

        return LineType.DIALOG
    }

    fun getTrimmedLine(): String {
        return line
    }
}
