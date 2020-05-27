package com.win.ft_home.model.navigation

/**
 * Create by liwen on 2020/5/26
 */
data class NavigationItemSub(
    val id:Int,
    val author: String,
    val desc: String,
    val envelopePic: String,
    val link: String,
    val niceDate: String,
    val title: String,
    val shareUser: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavigationItemSub

        if (id != other.id) return false
        if (author != other.author) return false
        if (desc != other.desc) return false
        if (envelopePic != other.envelopePic) return false
        if (link != other.link) return false
        if (niceDate != other.niceDate) return false
        if (title != other.title) return false
        if (shareUser != other.shareUser) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + author.hashCode()
        result = 31 * result + desc.hashCode()
        result = 31 * result + envelopePic.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + niceDate.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + shareUser.hashCode()
        return result
    }
}