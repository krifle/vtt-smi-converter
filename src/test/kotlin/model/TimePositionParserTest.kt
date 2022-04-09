package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import kotlin.test.assertTrue

internal class TimePositionParserTest {

    @Test
    fun `HHmmsszzz timestamp 로 변환`() {
        // given
        val line = Line("00:00:11.000 --> 00:00:13.000")
        val sut = TimePositionParser(line)

        // when
        val position = sut.parse()

        // then
        assertThat(position.start).isEqualTo(11000L)
        assertThat(position.end).isEqualTo(13000)
    }

    @Test
    fun `parse 는 String 을 받아서 long 형식으로 변환시켜줌`() {
        // given
        val line = Line("00:00:11.000 --> 00:00:13.000")
        val sut = TimePositionParser(line)

        // when then HH:mm:ss.SSS 으로 파싱 시도
        assertThat(sut.parseToMilliSecond("00:00:11.000")).isEqualTo(11000)

        // when then HH:mm:ss 형식으로 파싱 시도
        assertThat(sut.parseToMilliSecond("00:00:11")).isEqualTo(11000)
    }

    @Test
    fun `parse 는 location 위치를 가지는 position 을 파싱한다`() {
        // given
        val line = Line("00:00:11.000 --> 00:00:13.000 position:90% align:right size:35%")
        val sut = TimePositionParser(line)

        // when
        val timePosition = sut.parse()

        // then
        assertThat(timePosition.location.position).isEqualTo("90%")
        assertThat(timePosition.location.align).isEqualTo("right")
        assertThat(timePosition.location.size).isEqualTo("35%")
    }
}
