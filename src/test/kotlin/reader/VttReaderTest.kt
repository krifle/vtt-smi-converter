package reader

import com.google.gson.Gson
import exception.InvalidFormatException
import model.LangClass
import org.apache.commons.io.FileUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
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

    @Test
    fun `가장 기본적인 vtt 읽기 테스트`() {
        // given
        val vttFile = File(tempDirectory, "basic.vtt")

        // when
        val result = VttReader(vttFile, LangClass.KRCC).read()

        // then
        val cues = result.getCueList()
        println(Gson().toJson(cues))
        assertThat(cues).hasSize(2)

        val regions = result.getRegionList()
        println(Gson().toJson(regions))
        assertThat(regions).hasSize(1)
    }

    @Test
    fun `샘플 vtt 를 읽어서 cues 를 만들어 보자`() {
        // given
        val vttFile = File(tempDirectory, "sample01.vtt")

        // when
        val result = VttReader(vttFile, LangClass.KRCC).read()

        // then
        println(Gson().toJson(result.getCueList()))
    }

    @Test
    fun `align 속성이 있는 vtt 생성 테스트`() {
        // given
        val vttFile = File(tempDirectory, "caption-with-align.vtt")

        // when
        val result = VttReader(vttFile, LangClass.KRCC).read()

        // then
        println(Gson().toJson(result.getCueList()))
    }

    @Test
    fun `vtt 가 WEBVTT 로 시작하지 않으면 포멧 에러 발생`() {
        // given
        val vttFile = File(tempDirectory, "invalidHeader.vtt")

        // when // then
        assertThrows(InvalidFormatException::class.java) {
            VttReader(vttFile, LangClass.KRCC).read()
        }
    }

    @Test
    fun `vtt-with-text-header 읽기 테스트`() {
        // given
        val vttFile = File(tempDirectory, "vtt-with-text-header.vtt")

        // when
        val result = VttReader(vttFile, LangClass.KRCC).read()

        // then
        assertThat(result.getTitle()).isEqualTo("This file has cues.")
        println(Gson().toJson(result))
    }

    @Test
    fun `vtt-with-note 읽기 테스트`() {
        // given
        val vttFile = File(tempDirectory, "vtt-with-note.vtt")

        // when
        val result = VttReader(vttFile, LangClass.KRCC).read()

        // then
        assertThat(result.getNoteList()).hasSize(2)
        println(Gson().toJson(result.getNoteList()))
    }
}
