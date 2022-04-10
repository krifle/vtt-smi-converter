import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class MainKtTest {

    companion object {
        @TempDir
        lateinit var tempDirectory: File

        @BeforeAll
        @JvmStatic
        fun init() {
            File(this.javaClass.getResource("/samples/").path)
                .listFiles()!!
                .asList()
                .forEach {
                    FileUtils.copyFile(it, File(tempDirectory, it.name))
                }
        }
    }

    @Test
    fun runTest() {
        // given
        val koVttFile = File(tempDirectory, "kr.vtt")
        val enVttFile = File(tempDirectory, "en.vtt")
        val outputSmiFile = File(tempDirectory, "out.smi")
        val args = listOf(
            koVttFile.absolutePath, "KRCC",
            enVttFile.absolutePath, "ENCC",
            outputSmiFile.absolutePath
        )

        // when
        main(args.toTypedArray())
    }
}
