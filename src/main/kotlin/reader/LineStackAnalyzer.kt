package reader

import exception.IllegalCueMakingException
import exception.IllegalRegionMakingException
import model.*
import java.util.*

class LineStackAnalyzer(lineStack: Stack<Line>) {

    private val lineList = lineStack.toList().filter { it.text.isNotEmpty() }

    fun isCue(): Boolean {
        return lineList.filter { it.getType() == LineType.POSITION }.size == 1
    }

    fun toCue(): Cue {
        if (!isCue()) {
            val lineTexts = lineList.map { it.text }
            throw IllegalCueMakingException("invalid cue making from list => $lineTexts")
        }
        return CueParser(lineList).parse()
    }

    fun isRegion(): Boolean {
        return lineList.filter { it.getType() == LineType.REGION }.size == 1
    }

    fun toRegion(): Region {
        if (!isRegion()) {
            val lineTexts = lineList.map { it.text }
            throw IllegalRegionMakingException("invalid region making from list => $lineTexts")
        }

        return RegionParser(lineList).parse()
    }

    fun isNote(): Boolean {
        return lineList.any { it.getType() == LineType.NOTE }
    }

    fun toNote(): Note {
        val lineTexts = lineList.map { it.text }
        if (!isNote()) {
            throw IllegalRegionMakingException("invalid note making from list => $lineTexts")
        }
        return Note(lineTexts.joinToString("\n"))
    }
}
