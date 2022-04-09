package model

enum class LangClass(
    val className: String,
    val lang: String,
    val samiType: String
) {
    KRCC("한국어", "ko-KR", "CC"),
    ENCC("English", "en-US", "CC"),
    JACC("日本語", "ja-JP", "CC"),
    CHCC("中国人", "zh-cn-CN", "CC"),
    NONE("", "", "");

    fun writeAsStyle(): String {
        return ".$name { Name: $className; lang: $lang; SAMIType: $samiType; }"
    }
}
