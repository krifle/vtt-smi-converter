package model

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RegionTest {
    @Test
    fun `empty 체크`() {
        assertTrue(Region().isEmpty())
    }
}
