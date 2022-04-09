package model

class CueParser(
    private val lineList: List<Line>
) {
    fun parse(): Cue {
        val identifierLine = lineList.first()
        val identifier = if (identifierLine.getType() == LineType.POSITION) "" else identifierLine.text.trim()

        val positionLine = lineList.first { it.getType() == LineType.POSITION }
        val timePosition = TimePositionParser(positionLine).parse()

        val dialog = lineList.last()

        return Cue(identifier, timePosition, dialog.text.trim())
    }
}
