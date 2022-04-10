package writer

import converter.Converter
import model.Vtt
import java.io.File

class SamiWriter(
    private val vttList: List<Vtt>,
    private val outputSmi: File
) {
    fun write(): File {
        val samiText = vttToSamiText()
        outputSmi.writeText(samiText, Charsets.UTF_8)
        return outputSmi
    }

    internal fun vttToSamiText(): String {
        val sami = Converter(vttList).convert()

        val stringBuffer = StringBuffer()

        stringBuffer.append("<SAMI>\n")
        stringBuffer.append("<HEAD>\n")
        stringBuffer.append("<TITLE>")
        stringBuffer.append(sami.head.title.title)
        stringBuffer.append("</TITLE>\n")
        stringBuffer.append("<STYLE TYPE=\"text/css\">\n")
        stringBuffer.append(sami.head.style.text)
        stringBuffer.append("</STYLE>\n")
        stringBuffer.append("</HEAD>\n")
        stringBuffer.append("<BODY>\n")
        sami.body.syncList.forEach {
            stringBuffer.append(it.write())
            stringBuffer.append("\n")
        }
        stringBuffer.append("</BODY>\n")
        stringBuffer.append("</SAMI>\n")

        return stringBuffer.toString()
    }
}
