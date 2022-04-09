package model

class LocationParser(str: String) {

    companion object {
        private val properties = listOf("vertical:", "line:", "position:", "align:", "size:")
    }

    private val strWithoutWhitespace = str.filter { !it.isWhitespace() }.trim()

    private val propertiesWithIndex: Map<String, Int> = properties
        .associateWith { strWithoutWhitespace.lowercase().indexOf(it) }
        .toList()
        .sortedBy { it.second }
        .toMap()

    private val sortedIndex: List<Int> = propertiesWithIndex.values.toList()

    fun getLocation(): Location {
        return Location(
            vertical = get("vertical:"),
            line = get("line:"),
            position = get("position:"),
            align = get("align:"),
            size = get("size:")
        )
    }

    internal fun get(propertyName: String): String {
        val index = propertiesWithIndex[propertyName] ?: return ""

        if (index < 0) {
            return ""
        }

        val nextIndex = getNextIndex(index)

        return strWithoutWhitespace
            .substring(index, nextIndex)
            .replace(propertyName, "")
            .trim()
    }

    private fun getNextIndex(index: Int): Int {
        val next = sortedIndex.firstOrNull { it > index } ?: Int.MAX_VALUE
        return if (next > strWithoutWhitespace.length) {
            strWithoutWhitespace.length
        } else {
            next
        }
    }
}
