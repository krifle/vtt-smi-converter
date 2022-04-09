package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

internal class PositionParserTest {

    @Test
    fun `HHmmsszzz timestamp 로 변환`() {
        // given
        val line = Line("00:00:11.000 --> 00:00:13.000")
        val sut = PositionParser(line)

        // when
        val position = sut.parseToMilliSecond()

        // then
        assertThat(position.start).isEqualTo(11000L)
        assertThat(position.end).isEqualTo(13000)
    }

    @Test
    fun `parse 는 String 을 받아서 long 형식으로 변환시켜줌`() {
        // given
        val line = Line("00:00:11.000 --> 00:00:13.000")
        val sut = PositionParser(line)

        // when then HH:mm:ss.SSS 으로 파싱 시도
        assertThat(sut.parseToMilliSecond("00:00:11.000")).isEqualTo(11000)

        // when then HH:mm:ss 형식으로 파싱 시도
        assertThat(sut.parseToMilliSecond("00:00:11")).isEqualTo(11000)
    }

    @Test
    fun `SimpleDateFormat 학습 테스트`() {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS")
        val reference = simpleDateFormat.parse("00:00:00.000")
        val position = simpleDateFormat.parse("00:00:11.324")
        val milliSecond = position.time - reference.time

        assertThat(milliSecond).isEqualTo(11324)

        val converted = simpleDateFormat.format(11324 + reference.time)
        assertThat(converted).isEqualTo("00:00:11.324")
    }
}
