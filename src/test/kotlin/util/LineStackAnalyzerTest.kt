package util

import exception.IllegalCueMakingException
import model.Line
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

internal class LineStackAnalyzerTest {

    @Test
    fun `line 들 분석 결과 cue 타입이 아니라면 예외 발생`() {
        // given
        val lines = Stack<Line>()
        lines.push(Line("STYLE"))
        lines.push(Line("::cue {"))
        lines.push(Line("  background-image: linear-gradient(to bottom, dimgray, lightgray);"))
        lines.push(Line("  color: papayawhip;"))
        lines.push(Line("}"))
        lines.push(Line("/* Style blocks cannot use blank lines nor \"dash dash greater than\" */"))

        val sut = LineStackAnalyzer(lines)

        // when
        assertThrows(IllegalCueMakingException::class.java) {
            sut.toCue()
        }
    }

    @Test
    fun `가장 일반적인 line 들을 분석하여 cue 로 만들어 준다`() {
        // given
        val lines = Stack<Line>()
        lines.push(Line("00:11.000 --> 00:13.000"))
        lines.push(Line("<v Roger Bingham>We are in New York City"))

        val sut = LineStackAnalyzer(lines)

        // when
        val cue = sut.toCue()

        // then
        assertThat(cue.identifier).isEmpty()
        assertThat(cue.position.start).isEqualTo(11000L)
        assertThat(cue.position.end).isEqualTo(13000L)
        assertThat(cue.dialog).isEqualTo("<v Roger Bingham>We are in New York City")
        assertTrue(cue.position.location.isEmpty())
    }

    @Test
    fun `identifier 를 가지는 cue 를 분석한다`() {
        // given
        val lines = Stack<Line>()
        lines.push(Line("test"))
        lines.push(Line("00:00.000 --> 00:02.000"))
        lines.push(Line("This is a test."))

        val sut = LineStackAnalyzer(lines)

        // when
        val cue = sut.toCue()

        // then
        assertThat(cue.identifier).isEqualTo("test")
        assertThat(cue.position.start).isEqualTo(0L)
        assertThat(cue.position.end).isEqualTo(2000L)
        assertThat(cue.dialog).isEqualTo("This is a test.")
        assertThat(cue.position.location.isEmpty())
    }

    @Test
    fun `location 을 가지는 cue 를 분석한다`() {
        // given
        val lines = Stack<Line>()
        lines.push(Line("test"))
        lines.push(Line("00:00.000 --> 00:02.000 position:10%,line-left align:left size:35%"))
        lines.push(Line("This is a test."))

        val sut = LineStackAnalyzer(lines)

        // when
        val cue = sut.toCue()

        // then
        assertThat(cue.identifier).isEqualTo("test")
        assertThat(cue.position.start).isEqualTo(0L)
        assertThat(cue.position.end).isEqualTo(2000)
        assertThat(cue.position.location.position).isEqualTo("10%,line-left")
        assertThat(cue.position.location.align).isEqualTo("left")
        assertThat(cue.position.location.size).isEqualTo("35%")
        assertThat(cue.position.location.region).isEmpty()
        assertThat(cue.dialog).isEqualTo("This is a test.")
    }
}
