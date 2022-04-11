package model

class VttBuilder {
    private var title: String = ""
    private var langClass = LangClass.KRCC
    private val cues = mutableListOf<Cue>()
    private val regions = mutableListOf<Region>()
    private val notes = mutableListOf<Note>()

    fun addTitle(title: String) {
        this.title = title
    }

    fun addLangClass(langClass: LangClass) {
        this.langClass = langClass
    }

    fun addCue(cue: Cue) {
        this.cues.add(cue)
    }

    fun addRegion(region: Region) {
        this.regions.add(region)
    }

    fun addNote(note: Note) {
        this.notes.add(note)
    }

    fun build(): Vtt {
        return Vtt(
            title = this.title,
            langClass = this.langClass,
            cues = this.cues.toList(),
            regions = this.regions.toList(),
            notes = this.notes.toList()
        )
    }
}
