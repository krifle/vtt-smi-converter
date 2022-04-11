package reader

import exception.InvalidFormatException
import model.*
import java.io.File
import java.util.*

class VttReader(
    private val inputVtt: File,
    private val langClass: LangClass
) {
    private val vttBuilder = VttBuilder()
    private val lineStack = Stack<Line>()
    private val lines = inputVtt.readLines()

    fun read(): Vtt {
        if (lines.isEmpty()) {
            throw InvalidFormatException("Empty vtt file => ${inputVtt.absolutePath}")
        }

        if (!lines[0].trim().startsWith("WEBVTT")) {
            throw InvalidFormatException("Invalid WEBVTT file format => ${inputVtt.absolutePath}")
        }

        run lineBreaker@ {
            lines.forEach lineContinue@ {
                val line = Line(it)
                val lineType = line.getType()

                if (lineType == LineType.HEADER) {
                    val headerStartIndex = line.text.indexOf("-") + 1
                    vttBuilder.addTitle(line.text.substring(headerStartIndex, line.text.length).trim())
                    return@lineContinue
                }

                if (lineType == LineType.EMPTY) {
                    consumeStack()
                }

                lineStack.push(line)
            }
        }
        if (lineStack.isNotEmpty()) {
            consumeStack()
        }

        vttBuilder.addLangClass(langClass)
        return vttBuilder.build()
    }

    fun dryText(): String {
        return lines.joinToString("\n")
    }

    private fun consumeStack() {
        val lineStackAnalyzer = LineStackAnalyzer(lineStack)
        if (lineStackAnalyzer.isCue()) {
            vttBuilder.addCue(lineStackAnalyzer.toCue())
        }
        if (lineStackAnalyzer.isRegion()) {
            vttBuilder.addRegion(lineStackAnalyzer.toRegion())
        }
        if (lineStackAnalyzer.isNote()) {
            vttBuilder.addNote(lineStackAnalyzer.toNote())
        }
        lineStack.clear()
    }
}
