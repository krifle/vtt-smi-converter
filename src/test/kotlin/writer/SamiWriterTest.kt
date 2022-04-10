package writer

import model.LangClass
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import reader.VttReader
import java.io.File

internal class SamiWriterTest {

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
    fun `vttToSamiText 로 샘플 vtt 파일을 smi 형태 텍스트로 출력`() {
        // given
        val vttFile = File(tempDirectory, "basic.vtt")
        val vtt = VttReader(vttFile, LangClass.KRCC).read()
        val outputFile = File(tempDirectory, "writeTest.smi")

        // when
        val sut = SamiWriter(listOf(vtt), outputFile).vttToSamiText()

        // then
        println(sut)
    }

    @Test
    fun `vttToSamiText 로 샘플 파일 smi 형태 텍스트로 출력`() {
        // given
        val vttFile = File(tempDirectory, "sample01.vtt")
        val vtt = VttReader(vttFile, LangClass.KRCC).read()
        val outputFile = File(tempDirectory, "writeTest.smi")

        // when
        val sut = SamiWriter(listOf(vtt), outputFile).vttToSamiText()

        // then
        println(sut)
    }
}
