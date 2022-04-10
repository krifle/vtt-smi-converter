package input

import exception.InvalidInputException
import model.LangClass
import java.io.File

class InputValidator(val args: List<String>) {

    private lateinit var inputVttList: List<String>
    private lateinit var outputSmi: String

    fun validate(): Arguments {
        if (args.isEmpty() || args.size < 2) {
            throw InvalidInputException("argument ${args.joinToString(", ")}")
        }

        outputSmi = args.last().trim()
        inputVttList = args.dropLast(1)

        val inputVttFiles = inputVttList
            .filterIndexed { index, _ ->
                index % 2 == 0 // even index for vtt file path
            }
            .map {
                if (! it.lowercase().endsWith(".vtt")) {
                    throw InvalidInputException("first argument should be .vtt")
                }
                return@map getInputFile(it)
            }

        if (! outputSmi.lowercase().endsWith(".smi") && ! outputSmi.lowercase().endsWith(".sami")) {
            throw InvalidInputException("second argument should be .smi or .sami")
        }

        val inputLangClassList = inputVttList
            .filterIndexed { index, _ -> index % 2 == 1 } // odd index for langClass
            .map { LangClass.valueOf(it.uppercase()) }
        val outputSmiFile = getOutputFile(outputSmi)

        return Arguments(inputVttFiles, inputLangClassList, outputSmiFile)
    }

    private fun getInputFile(path: String): File {
        val currentDirectory = File(System.getProperty("user.dir"))
        val absoluteFile = File(path)
        if (absoluteFile.exists()) {
            return absoluteFile
        }

        if (path.contains(File.separator)) {
            throw InvalidInputException("argument should be absolute or relative file path.")
        }

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
