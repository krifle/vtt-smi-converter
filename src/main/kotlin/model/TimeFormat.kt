package model

enum class TimeFormat(
    val format: String
) {
    HHMMSSZZZ("HH:mm:ss.SSS"),
    MMSSZZZ("mm:ss.SSS"),
    HHMMSS("HH:mm:ss"),
    MMSS("mm:ss");
}
