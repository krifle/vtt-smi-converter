package util

import exception.InvalidFormatException
import model.CueType
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class VttReaderTest {
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

    fun `샘플 vtt 를 읽어서 cues 를 만들어 보자`() {
        // given
        val vttFile = File(tempDirectory, "sample01.vtt")

        // when
        val result = VttReader(vttFile).read()

        // then
        result.cues.forEach {
            if (it.type == CueType.LINE) {
                println(it.dialog!!)
            }
        }
    }

    fun `vtt 가 WEBVTT 로 시작하지 않으면 포멧 에러 발생`() {
        // given
        val vttFile = File(tempDirectory, "invalidHeader.vtt")

        // when // then
        assertThrows(InvalidFormatException::class.java) {
            VttReader(vttFile).read()
        }
    }
}
