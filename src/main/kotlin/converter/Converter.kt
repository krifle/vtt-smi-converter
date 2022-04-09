package converter

import model.LangClass
import model.Vtt
import writer.*

class Converter(
    private val vtt: Vtt
) {
    companion object {
        private val defaultPStyle = """
            margin-left:8pt; margin-right:8pt; margin-bottom:2pt;
            margin-top:2pt; font-size:14pt; text-align:center;
            font-family:굴림, Arial; font-weight:normal; color:white;
            background-color:black;
        """.trimIndent()
    }

    private val title: String = "" // TODO input 으로 받기
    private val langClass = LangClass.KRCC // TODO input 으로 받기

    fun convert(): Sami {
        val titleTag = TitleTag(title)
        val style = """
            <!--
            P { $defaultPStyle }
            ${langClass.writeAsStyle()}
            #STDPrn { Name:Standard Print; }
            #LargePrn { Name:Large Print; font-size:20pt; }
            #SmallPrn { Name:Small Print; font-size:10pt; }
            -->
        """.trimIndent()
        val styleTag = StyleTag(style)
        val headTag = HeadTag(titleTag, styleTag)
        val bodyTag = BodyTag(vtt.cuesToSyncTagList(langClass))
        return Sami(
            head = headTag,
            body = bodyTag
        )
    }
}
