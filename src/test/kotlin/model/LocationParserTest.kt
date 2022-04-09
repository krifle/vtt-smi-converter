package model

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class LocationParserTest {

    @Test
    fun `vertical 속성 얻어옴`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val result = sut.get("vertical:")

        // then
        assertThat(result).isEqualTo("rl")
    }

    @Test
    fun `line 속성 얻어옴`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val result = sut.get("line:")

        // then
        assertThat(result).isEqualTo("center")
    }

    @Test
    fun `position 속성 얻어옴 1`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val result = sut.get("position:")

        // then
        assertThat(result).isEqualTo("90%,line-left")
    }

    @Test
    fun `position 속성 얻어옴 2`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90% align:right size:35%")

        // when
        val result = sut.get("position:")

        // then
        assertThat(result).isEqualTo("90%")
    }

    @Test
    fun `align 속성 얻어옴`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val result = sut.get("align:")

        // then
        assertThat(result).isEqualTo("right")
    }

    @Test
    fun `size 속성 얻어옴`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val result = sut.get("size:")

        // then
        assertThat(result).isEqualTo("35%")
    }

    @Test
    fun `end 단의 문자열에서 location 이 없으면 empty location 을 만든다`() {
        // given
        val sut = LocationParser("00:00:13.000")

        // when
        val location = sut.getLocation()

        // then
        assertTrue(location.isEmpty())
    }

    @Test
    fun `end 단의 문자열을 받아 location 을 뽑아낸다`() {
        // given
        val sut = LocationParser("00:00:13.000 vertical:rl line:center position:90%,line-left align:right size:35%")

        // when
        val location = sut.getLocation()

        // then
        assertThat(location.vertical).isEqualTo("rl")
        assertThat(location.line).isEqualTo("center")
        assertThat(location.position).isEqualTo("90%,line-left")
        assertThat(location.align).isEqualTo("right")
        assertThat(location.size).isEqualTo("35%")
    }
}
