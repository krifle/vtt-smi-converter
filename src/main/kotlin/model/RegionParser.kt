package model

class RegionParser(
    lineList: List<Line>
) {
    private val whitespaceRemovedLineList = lineList
        .map { it.text.replace("\\s".toRegex(), "").lowercase() }

    fun parse(): Region {
        val id = whitespaceRemovedLineList.firstOrNull { it.contains("id:") } ?: ""
        val width = whitespaceRemovedLineList.firstOrNull { it.contains("width:") } ?: ""
        val lines = whitespaceRemovedLineList.firstOrNull { it.contains("lines:") } ?: "-1"
        val regionAnchor = whitespaceRemovedLineList.firstOrNull { it.contains("regionanchor:") } ?: ""
        val viewPortAnchor = whitespaceRemovedLineList.firstOrNull { it.contains("viewportanchor:") } ?: ""
        val scroll = whitespaceRemovedLineList.firstOrNull { it.contains("scroll:") } ?: ""
        return Region(
            id = id.replace("id:", ""),
            width = width.replace("width:", ""),
            lines = lines.replace("lines:", "").toInt(),
            regionAnchor = regionAnchor.replace("regionanchor:", ""),
            viewPortAnchor = viewPortAnchor.replace("viewportanchor:", ""),
            scroll = scroll.replace("scroll:", "")
        )
    }
}
