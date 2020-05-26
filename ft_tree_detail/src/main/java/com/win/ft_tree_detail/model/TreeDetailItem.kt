package com.win.ft_tree_detail.model

/**
 * Create by liwen on 2020-05-22
 */
data class TreeDetailItem(
    val id: Int,
    val chapterName: String,
    val superChapterName: String,
    val link: String,
    val shareUser: String,
    val niceDate: String,
    val title: String,
    val author: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TreeDetailItem

        if (id != other.id) return false
        if (chapterName != other.chapterName) return false
        if (superChapterName != other.superChapterName) return false
        if (link != other.link) return false
        if (shareUser != other.shareUser) return false
        if (niceDate != other.niceDate) return false
        if (title != other.title) return false
        if (author != other.author) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + chapterName.hashCode()
        result = 31 * result + superChapterName.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + shareUser.hashCode()
        result = 31 * result + niceDate.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}