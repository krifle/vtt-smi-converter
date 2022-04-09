import util.InputValidator

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size != 2) {
        println("Use format : vtt-converter [input.vtt] [output.smi]")
        return
    }
    InputValidator(args.toList()).validate()

    println("Program arguments: ${args.joinToString()}")
}
