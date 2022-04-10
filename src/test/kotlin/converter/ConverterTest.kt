package converter

import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import reader.VttReader
import java.io.File

internal class ConverterTest {

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
    fun `convert 동작 테스트`() {
        // given
        val vttFile = File(tempDirectory, "basic.vtt")
        val vtt = VttReader(vttFile).read()

        // when
        val sut = Converter(listOf(vtt))
        val sami = sut.convert()

        // then
        println(Gson().toJson(sami))
    }
}
