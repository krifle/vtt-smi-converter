package model

data class Cue(
    val identifier: String = "",
    val position: TimePosition = TimePosition(),
    val dialog: String = ""
)
