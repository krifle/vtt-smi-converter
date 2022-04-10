import input.InputValidator
import reader.VttReader
import writer.SamiWriter

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size != 2) {
        println("""
            vtt-converter

            Converts VTT to SMI.
            Multiple language supported.
            To make multiple language SMI, provide multiple VTT files with corresponding langClasses.

            Use format : java -jar vtt-converter-1.0-SNAPSHOT.jar [[input1.vtt] [langClass1] ..] [output.smi]

            Where langClass are:
            - KRCC : For Korean
            - ENCC : For English
            - JACC : For Japanese
            - CHCC : For Chinese
        """.trimIndent())
        return
    }

    println("Program arguments: ${args.joinToString()}\n")

    val arguments = InputValidator(args.toList()).validate()

    val vttList = arguments.inputVttList
        .map {
            println("File read from ${it.absolutePath} : \n")
            val vttReader = VttReader(it)
            println(vttReader.dryText())
            return@map vttReader
        }
        .map {
            it.read()
        }

    val samiWriter = SamiWriter(vttList, arguments.outputSmi)

    println("Result on ${arguments.outputSmi.absolutePath} : ")
    println(samiWriter.vttToSamiText())

    samiWriter.write()
}
