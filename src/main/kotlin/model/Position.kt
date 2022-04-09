package model

import java.text.SimpleDateFormat

data class Position(
    val start: Long,
    val end: Long
) {
    companion object {
        private val reference = SimpleDateFormat("HH:mm:ss.SSS").parse("00:00:00.000")
    }

    fun startAs(timeFormat: TimeFormat): String {
        val simpleDateFormat = SimpleDateFormat(timeFormat.format)
        return simpleDateFormat.format(start + reference.time)
    }

    fun endAs(timeFormat: TimeFormat): String {
        val simpleDateFormat = SimpleDateFormat(timeFormat.format)
        return simpleDateFormat.format(end + reference.time)
    }
}
