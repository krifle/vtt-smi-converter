package converter

import model.Vtt
import writer.*

class Converter(
    private val vttList: List<Vtt>
) {
    companion object {
        private val defaultPStyle = """
margin-left:8pt; margin-right:8pt; margin-bottom:2pt;
margin-top:2pt; font-size:14pt; text-align:center;
font-family:굴림, Arial; font-weight:normal; color:white;
background-color:black;
        """.trimIndent()
    }

    private val title: String = vttList.first().title

    fun convert(): Sami {
        val titleTag = TitleTag(title)
        val style = """
<!--
P { $defaultPStyle }
#STDPrn { Name:Standard Print; }
#LargePrn { Name:Large Print; font-size:20pt; }
#SmallPrn { Name:Small Print; font-size:10pt; }
${printLangClass()}
-->

        """.trimIndent()
        val styleTag = StyleTag(style)
        val headTag = HeadTag(titleTag, styleTag)
        val bodyTag = BodyTag(vttList.flatMap { it.cuesToSyncTagList() })
        return Sami(
            head = headTag,
            body = bodyTag
        )
    }

    private fun printLangClass(): String {
        val stringBuffer = StringBuffer()
        vttList.map { it.langClass }.forEach {
            stringBuffer.append(it.writeAsStyle())
            stringBuffer.append("\n")
        }
        return stringBuffer.toString()
    }
}
