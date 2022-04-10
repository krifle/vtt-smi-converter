import input.InputValidator
import reader.VttReader
import writer.SamiWriter

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size != 2) {
        println("Use format : java -jar vtt-converter-1.0-SNAPSHOT.jar [input.vtt] [output.smi]")
        return
    }

    println("Program arguments: ${args.joinToString()}")

    val arguments = InputValidator(args.toList()).validate()


    val vttReader = VttReader(arguments.inputVtt)
    println("File read from ${arguments.inputVtt.absolutePath} : ")
    println(vttReader.dryText())

    val vtt = vttReader.read()
    val samiWriter = SamiWriter(vtt, arguments.outputSmi)

    println("Result on ${arguments.outputSmi.absolutePath} : ")
    println(samiWriter.vttToSamiText())

    samiWriter.write()
}
