package model

data class Location(
    val position: String = "",
    val align: String = "",
    val size: String = "",
    val region: String = ""
) {
    fun isEmpty(): Boolean {
        return position == "" && align == "" && size == "" && region == ""
    }
}
