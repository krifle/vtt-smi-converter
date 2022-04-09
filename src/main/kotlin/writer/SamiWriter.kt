package writer

import model.Vtt
import java.io.File

class SamiWriter(
    private val vtt: Vtt,
    private val outputSmi: File
) {
    fun write(): File {
        return outputSmi
    }
}
