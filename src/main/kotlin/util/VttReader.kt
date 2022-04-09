package util

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

        if (lines[0].trim() != "WEBVTT") { // check for WebVTT header
            throw InvalidFormatException("Invalid WEBVTT file => ${inputVtt.absolutePath}")
        }

        run lineBreaker@ {
            lines.forEach lineContinue@ {
                val line = Line(it)
                val lineType = line.getType()

                if (lineType == LineType.HEADER) { // 헤더는 넘긴다
                    return@lineContinue
                }

                if (lineType == LineType.EMPTY) { // EMPTY 일 때 lineStack 에 있는 걸 처리한다. Stack 에 있는게 어떤 종류인지 판단한다
                    val lineStackAnalyzer = LineStackAnalyzer(lineStack)

                    if (lineStackAnalyzer.isCue()) {
                        vtt.cues.add(lineStackAnalyzer.toCue())
                    }

                    lineStack.clear()
                }

                lineStack.push(line)
            }
        }

        return vtt
    }
}
