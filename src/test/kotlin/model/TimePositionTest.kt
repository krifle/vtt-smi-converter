package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TimePositionTest {

    @Test
    fun `String 형태로 변환 테스트`() {
        // given
        val sut = TimePosition(14000L, 19000L, Location())

        // when
        val start = sut.startAs(TimeFormat.HHMMSSZZZ)
        val end = sut.endAs(TimeFormat.HHMMSS)

        // then
        assertThat(start).isEqualTo("00:00:14.000")
        assertThat(end).isEqualTo("00:00:19")
    }
}
