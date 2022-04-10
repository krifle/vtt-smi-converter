package reader

import exception.InvalidFormatException
import model.LangClass
import model.Line
import model.LineType
import model.Vtt
import java.io.File
import java.util.*

class VttReader(
    private val inputVtt: File,
    private val langClass: LangClass
) {
    private val vtt = Vtt()
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
                    vtt.setTitle(line.text.substring(headerStartIndex, line.text.length).trim())
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

        vtt.setLangClass(langClass)
        return vtt
    }

    fun dryText(): String {
        return lines.joinToString("\n")
    }

    private fun consumeStack() {
        val lineStackAnalyzer = LineStackAnalyzer(lineStack)
        if (lineStackAnalyzer.isCue()) {
            vtt.addCue(lineStackAnalyzer.toCue())
        }
        if (lineStackAnalyzer.isRegion()) {
            vtt.addRegion(lineStackAnalyzer.toRegion())
        }
        if (lineStackAnalyzer.isNote()) {
            vtt.addNote(lineStackAnalyzer.toNote())
        }
        lineStack.clear()
    }
}
