package util

import exception.IllegalCueMakingException
import model.Cue
import model.Line
import model.LineType
import model.TimePositionParser
import java.util.*

class LineStackAnalyzer(lines: Stack<Line>) {

    private val lineList = lines.toList().filter { it.text.isNotEmpty() }

    fun toCue(): Cue {
        if (!isCue()) {
            val lineTexts = lineList.map { it.text }
            throw IllegalCueMakingException("invalid cue making from list => $lineTexts")
        }

        val identifierLine = lineList.first()
        val identifier = if (identifierLine.getType() == LineType.POSITION) "" else identifierLine.text

        val thePosition = lineList.first { it.getType() == LineType.POSITION }
        val timePosition = TimePositionParser(thePosition).parse()

        val dialog = lineList.last()

        return Cue(identifier, timePosition, dialog.text)
    }

    fun isCue(): Boolean {
        return lineList.filter { it.getType() == LineType.POSITION }.size == 1
    }
}
