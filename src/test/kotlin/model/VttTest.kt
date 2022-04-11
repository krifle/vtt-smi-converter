package model

import com.google.gson.Gson
import org.junit.jupiter.api.Test

class VttTest {

    @Test
    fun `cues 변환 테스트`() {
        // given
        val vttBuilder = VttBuilder()
        vttBuilder.addCue(Cue("", TimePosition(1000L, 3000L, Location()), "Forma tibi famam peperit: sed filia matrem"))
        vttBuilder.addCue(Cue("", TimePosition(3000L, 6000L, Location()), "Occidit, formam non bona fama bonam."))
        val sut = vttBuilder.build()

        // when
        val result = sut.cuesToSyncTagList()

        // then
        println(Gson().toJson(result))
    }
}
