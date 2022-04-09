package model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CueParserTest {

    @Test
    fun `기본적인 lineList 에서 cue 를 만들어냄`() {
        // given
        val lineList = listOf(
            Line("00:11.000 --> 00:13.000"),
            Line("<v Roger Bingham>We are in New York City")
        )

        // when
        val cue = CueParser(lineList).parse()

        // then
        Assertions.assertThat(cue.identifier).isEmpty()
        Assertions.assertThat(cue.position.start).isEqualTo(11000L)
        Assertions.assertThat(cue.position.end).isEqualTo(13000L)
        Assertions.assertThat(cue.dialog).isEqualTo("<v Roger Bingham>We are in New York City")
        org.junit.jupiter.api.Assertions.assertTrue(cue.position.location.isEmpty())
    }

    @Test
    fun `identifier 를 가지는 lineList 에서 cue 를 만들어냄`() {
        // given
        val lineList = listOf(
            Line("test"),
            Line("00:00.000 --> 00:02.000 position:10%,line-left align:left size:35%"),
            Line("This is a test.")
        )

        // when
        val cue = CueParser(lineList).parse()

        // then
        Assertions.assertThat(cue.identifier).isEqualTo("test")
        Assertions.assertThat(cue.position.start).isEqualTo(0L)
        Assertions.assertThat(cue.position.end).isEqualTo(2000)
        Assertions.assertThat(cue.position.location.position).isEqualTo("10%,line-left")
        Assertions.assertThat(cue.position.location.align).isEqualTo("left")
        Assertions.assertThat(cue.position.location.size).isEqualTo("35%")
        Assertions.assertThat(cue.position.location.region).isEmpty()
        Assertions.assertThat(cue.dialog).isEqualTo("This is a test.")
    }

    @Test
    fun `location 을 가지는 lineList 에서 cue 만들어냄`() {
        // given
        val lineList = listOf(
            Line("test"),
            Line("00:00.000 --> 00:02.000 position:10%,line-left align:left size:35%"),
            Line("This is a test")
        )

        // when
        val cue = CueParser(lineList).parse()

        // then
        Assertions.assertThat(cue.identifier).isEqualTo("test")
        Assertions.assertThat(cue.position.start).isEqualTo(0L)
        Assertions.assertThat(cue.position.end).isEqualTo(2000)
        Assertions.assertThat(cue.position.location.position).isEqualTo("10%,line-left")
        Assertions.assertThat(cue.position.location.align).isEqualTo("left")
        Assertions.assertThat(cue.position.location.size).isEqualTo("35%")
        Assertions.assertThat(cue.position.location.region).isEmpty()
        Assertions.assertThat(cue.dialog).isEqualTo("This is a test")
    }
}
