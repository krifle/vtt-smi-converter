package model

data class Cue(
    val type: CueType,
    val position: Position,
    val dialog: String?
)
