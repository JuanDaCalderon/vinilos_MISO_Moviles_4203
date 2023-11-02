package com.example.vinilos

data class Album (
    val id:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val tracks: Array<String>,
    val performers: Array<String>,
    val comments: Array<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Album

        if (!tracks.contentEquals(other.tracks)) return false
        if (!performers.contentEquals(other.performers)) return false
        if (!comments.contentEquals(other.comments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tracks.contentHashCode()
        result = 31 * result + performers.contentHashCode()
        result = 31 * result + comments.contentHashCode()
        return result
    }
}