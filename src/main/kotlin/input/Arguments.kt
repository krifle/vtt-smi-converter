package input

import model.LangClass
import java.io.File

data class Arguments(
    val inputVttList: List<File>,
    val inputLangClassList: List<LangClass>,
    val outputSmi: File
)
