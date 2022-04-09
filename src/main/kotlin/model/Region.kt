package model

data class Region(
    val id: String = "",
    val width: String = "",
    val lines: Int = -1,
    val regionAnchor: String = "",
    val viewPortAnchor: String = "",
    val scroll: String = ""
) {
    fun isEmpty(): Boolean {
        return id == "" && width == "" && lines == -1 && regionAnchor == "" && viewPortAnchor == "" && scroll == ""
    }
}
