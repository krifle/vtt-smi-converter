package model

import exception.InvalidLineSeparatorException
import java.text.ParseException
import java.text.SimpleDateFormat

class TimePositionParser(
    private val line: Line
) {

    companion object {
        private val reference = SimpleDateFormat("HH:mm:ss.SSS").parse("00:00:00.000")
    }

    fun parse(): TimePosition {
        if (line.getType() != LineType.POSITION) {
            throw InvalidLineSeparatorException("trying to parse position from invalid line => $line")
        }

        val split = line.getTrimmedLine().split("-->")
        if (split.size != 2) {
            throw InvalidLineSeparatorException("invalid type of line => $line")
        }

        val start = split[0]
        val end = split[1]

        return TimePosition(parseToMilliSecond(start), parseToMilliSecond(end), Location())
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
        throw InvalidLineSeparatorException("invalid position => $str")
    }
}
