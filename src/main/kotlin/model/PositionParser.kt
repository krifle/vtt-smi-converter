package model

import exception.InvalidLineSeparator
import java.text.ParseException
import java.text.SimpleDateFormat

class PositionParser(
    val line: Line
) {

    companion object {
        private val reference = SimpleDateFormat("HH:mm:ss.SSS").parse("00:00:00.000")
    }

    fun parseToMilliSecond(): Position {
        if (line.getType() != LineType.POSITION) {
            throw InvalidLineSeparator("trying to parse position from invalid line => $line")
        }

        val split = line.getTrimmedLine().split("-->")
        if (split.size != 2) {
            throw InvalidLineSeparator("invalid type of line => $line")
        }

        return Position(0, 0)
    }

    internal fun parseToMilliSecond(str: String): Long {
        TimeFormat.values().forEach {
            try {
                val formatter = SimpleDateFormat(it.format)
                val pos = formatter.parse(str)
                return pos.time - reference.time
            } catch (_: ParseException) {
            }
        }
        throw InvalidLineSeparator("invalid position => $str")
    }
}
