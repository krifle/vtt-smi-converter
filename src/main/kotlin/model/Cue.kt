package model

data class Cue(
    val identifier: String = "",
    val position: TimePosition,
    val dialog: String
)
