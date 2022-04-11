package model

import writer.SyncTag

data class Vtt(
    val title: String,
    var langClass: LangClass,
    val cues: List<Cue>,
    val regions: List<Region>,
    val notes: List<Note>
) {
    fun cuesToSyncTagList(): List<SyncTag> {
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
