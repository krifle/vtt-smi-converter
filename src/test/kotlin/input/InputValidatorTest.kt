package input

import exception.InvalidInputException
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class InputValidatorTest {

    companion object {
        @TempDir
        lateinit var tempDirectory: File
        private lateinit var tempDirectoryPath: String

        @BeforeAll
        @JvmStatic
        fun init() {
            File(this.javaClass.getResource("/samples/").path)
                .listFiles()!!
                .asList()
                .forEach {
                    FileUtils.copyFile(it, File(tempDirectory, it.name))
                }
            tempDirectoryPath = tempDirectory.path
        }
    }

    @Test
    fun `argument 1개 이하 받으면 에러`() {
        // given 2 input 이 1개라면 에러
        val args = listOf(
            "$tempDirectoryPath/sample01.vtt"
        )

        // when // then
        assertThrows(InvalidInputException::class.java) {
            InputValidator(args).validate()
        }
    }

    @Test
    fun `argument 없어도 에러`() {
        // given
        val args = listOf<String>()

        // when // then
        assertThrows(InvalidInputException::class.java) {
            InputValidator(args).validate()
        }
    }

    @Test
    fun `argument 2개를 받으면 ok`() {
        // given 3 하지만 2개 input 이라면 OK
        val args = listOf(
            "$tempDirectoryPath/sample01.vtt",
            "KRCC",
            "$tempDirectoryPath/sample-kor-01.smi"
        )

        // when // then
        InputValidator(args).validate()
    }

    @Test
    fun `첫 번째 argument 는 vtt 형식이 아니면 에러 발생`() {
        // given
        val args = listOf(
            "$tempDirectoryPath/sample-kor-01.smi",
            "KRCC",
            "$tempDirectoryPath/sample-kor-eng-01.smi"
        )

        // when // then
        assertThrows(InvalidInputException::class.java) {
            InputValidator(args).validate()
        }
    }

    @Test
    fun `첫 번째 argument 는 존재하는 파일이어야 한다`() {
        // given
        val args = listOf(
            "$tempDirectoryPath/not-existing-file.vtt",
            "KRCC",
            "$tempDirectoryPath/sample-kor-eng-01.smi"
        )

        // when // then
        assertThrows(InvalidInputException::class.java) {
            InputValidator(args).validate()
        }
    }

    @Test
    fun `마지막 argument 는 smi 형식이 아니면 에러 발생`() {
        // given
        val args = listOf(
            "$tempDirectoryPath/sample-kor-01.vtt",
            "KRCC",
            "$tempDirectoryPath/sample01.vtt"
        )

        // when // then
        assertThrows(InvalidInputException::class.java) {
            InputValidator(args).validate()
        }
    }
}
