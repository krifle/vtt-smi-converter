package model

import writer.SyncTag

class Vtt {
    private var title: String = ""
    private val cues = mutableListOf<Cue>()
    private val regions = mutableListOf<Region>()
    private val notes = mutableListOf<Note>()

    fun setTitle(title: String) {
        this.title = title
    }

    fun getTitle(): String {
        return this.title
    }

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

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNoteList(): List<Note> {
        return notes
    }
}
