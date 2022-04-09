package model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RegionParserTest {

    @Test
    fun `Region 생성`() {
        // given
        val lineList = listOf(
            Line("REGION"),
            Line("id:bill"),
            Line("width:40%"),
            Line("lines:3"),
            Line("regionanchor:100%,100%"),
            Line("viewportanchor:90%,90%"),
            Line("scroll:up")
        )

        // when
        val result = RegionParser(lineList).parse()

        // then
        Assertions.assertThat(result.id).isEqualTo("bill")
        Assertions.assertThat(result.width).isEqualTo("40%")
        Assertions.assertThat(result.lines).isEqualTo(3)
        Assertions.assertThat(result.regionAnchor).isEqualTo("100%,100%")
        Assertions.assertThat(result.viewPortAnchor).isEqualTo("90%,90%")
        Assertions.assertThat(result.scroll).isEqualTo("up")
    }

    @Test
    fun `속성이 몇 개 없어도 잘 생성함`() {
        // given
        val lineList = listOf(
            Line("REGION"),
            Line("id:bill"),
            Line("regionanchor:100%,100%"),
            Line("scroll:up")
        )

        // when
        val result = RegionParser(lineList).parse()

        // then
        Assertions.assertThat(result.id).isEqualTo("bill")
        Assertions.assertThat(result.width).isEmpty()
        Assertions.assertThat(result.lines).isEqualTo(-1)
        Assertions.assertThat(result.regionAnchor).isEqualTo("100%,100%")
        Assertions.assertThat(result.viewPortAnchor).isEmpty()
        Assertions.assertThat(result.scroll).isEqualTo("up")
    }
}
