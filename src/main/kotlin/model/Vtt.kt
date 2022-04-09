package model

import writer.SyncTag

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

    fun cuesToSyncTagList(langClass: LangClass): List<SyncTag> {
        return cues.map {
            SyncTag(
                start = it.position.start,
                end = it.position.end,
                langClass = langClass,
                dialog = it.dialog
            )
        }
    }
}
