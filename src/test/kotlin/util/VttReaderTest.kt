package util

import exception.InvalidFormatException
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.util.*

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
    fun `샘플 vtt 를 읽어서 cues 를 만들어 보자`() {
        // given
        val vttFile = File(tempDirectory, "sample01.vtt")

        // when
        val result = VttReader(vttFile).read()

        // then
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

    @Test
    fun t() {
        val stack = Stack<Int>()
        stack.push(1)
        stack.push(2)
        stack.push(3)

        val list = stack.toList()

        println(list)
    }
}
