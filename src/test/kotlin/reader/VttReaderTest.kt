package reader

import com.google.gson.Gson
import exception.InvalidFormatException
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
        val result = VttReader(vttFile).read()

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
        val result = VttReader(vttFile).read()

        // then
        println(Gson().toJson(result.getCueList()))
    }

    @Test
    fun `align 속성이 있는 vtt 생성 테스트`() {
        // given
        val vttFile = File(tempDirectory, "caption-with-align.vtt")

        // when
        val result = VttReader(vttFile).read()

        // then
        println(Gson().toJson(result.getCueList()))
    }

    @Test
    fun `vtt 가 WEBVTT 로 시작하지 않으면 포멧 에러 발생`() {
        // given
        val vttFile = File(tempDirectory, "invalidHeader.vtt")

        // when // then
        assertThrows(InvalidFormatException::class.java) {
            VttReader(vttFile).read()
        }
    }

    @Test
    fun `Kotlin forEach 에서 break, continue 학습 테스트`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        run lineIterator@ {
            list.forEach lineContinue@ {
                println(it)
                if (it == 2) {
                    println("return@forEach") // continue
                    return@lineContinue
                }
                if (it == 4) {
                    println("return@lineIterator") // break
                    return@lineIterator
                }
                println("processed => $it")
            }
        }
    }
}
