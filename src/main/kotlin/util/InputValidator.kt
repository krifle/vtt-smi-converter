package util

import exception.InvalidInputException
import java.io.File

class InputValidator(val args: List<String>) {

    private lateinit var inputVtt: String
    private lateinit var outputSmi: String

    fun validate(): Arguments {
        if (args.isEmpty() || args.size != 2) {
            throw InvalidInputException("argument ${args.joinToString(", ")}")
        }

        inputVtt = args[0].trim()
        outputSmi = args[1].trim()

        if (! inputVtt.lowercase().endsWith(".vtt")) {
            throw InvalidInputException("first argument should be .vtt")
        }

        if (! outputSmi.lowercase().endsWith(".smi") && ! outputSmi.lowercase().endsWith(".sami")) {
            throw InvalidInputException("second argument should be .smi or .sami")
        }

        val inputVttFile = getInputFile(inputVtt)
        val outputSmiFile = getOutputFile(outputSmi)


        return Arguments(inputVttFile, outputSmiFile)
    }

    private fun getInputFile(path: String): File {
        val absoluteFile = File(path)
        if (absoluteFile.exists()) {
            return absoluteFile
        }

        if (path.contains(File.separator)) {
            throw InvalidInputException("argument should be absolute or relative file path.")
        }

        val currentDirectory = File(System.getProperty("user.dir"))
        val relativeFile = File(currentDirectory, path)
        if (relativeFile.exists()) {
            return relativeFile
        }

        throw InvalidInputException("such file does not exists => $path")
    }

    private fun getOutputFile(path: String): File {
        if (path.contains(File.separator)) {
            return File(path)
        }

        val currentDirectory = File(System.getProperty("user.dir"))
        return File(currentDirectory, path)
    }
}
