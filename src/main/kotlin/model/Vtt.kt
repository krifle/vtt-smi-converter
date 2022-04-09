package model

class Vtt {
    private val cues = mutableListOf<Cue>()
    private val regions = mutableListOf<Region>()

    fun addCue(cue: Cue) {
        cues.add(cue)
    }

    fun getCueList(): List<Cue> {
        return cues.toList()
    }

    fun addRegion(region: Region) {
        regions.add(region)
    }

    fun getRegionList(): List<Region> {
        return regions.toList()
    }
}
