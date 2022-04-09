package writer

import model.LangClass

data class SyncTag(
    val start: Long = 0L,
    val end: Long = 0L,
    val langClass: LangClass = LangClass.NONE,
    val dialog: String = ""
) {
    fun write(): String {
        return """
            <SYNC Start=$start>
            <P Class=${langClass.name}>$dialog</P>
            <SYNC Start=$end>
            <P Class=KRCC>&nbsp;</P>
        """.trimIndent()
    }
}
