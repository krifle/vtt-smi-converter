package reader

import exception.InvalidFormatException
import model.Line
import model.LineType
import model.Vtt
import java.io.File
import java.util.*

class VttReader(
    private val inputVtt: File
) {
    private val vtt = Vtt()
    private val lineStack = Stack<Line>()

    fun read(): Vtt {
        val lines = inputVtt.readLines()

        if (lines.isEmpty()) {
            throw InvalidFormatException("Empty vtt file => ${inputVtt.absolutePath}")
        }

        if (lines[0].trim() != "WEBVTT") {
            throw InvalidFormatException("Invalid WEBVTT file format => ${inputVtt.absolutePath}")
        }

        run lineBreaker@ {
            lines.forEach lineContinue@ {
                val line = Line(it)
                val lineType = line.getType()

                if (lineType == LineType.HEADER) {
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

        return vtt
    }

    private fun consumeStack() {
        val lineStackAnalyzer = LineStackAnalyzer(lineStack)
        if (lineStackAnalyzer.isCue()) {
            vtt.addCue(lineStackAnalyzer.toCue())
        }
        if (lineStackAnalyzer.isRegion()) {
            vtt.addRegion(lineStackAnalyzer.toRegion())
        }
        lineStack.clear()
    }
}
