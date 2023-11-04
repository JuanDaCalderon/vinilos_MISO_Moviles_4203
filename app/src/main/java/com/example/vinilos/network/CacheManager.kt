package com.example.vinilos.network

import android.content.Context
import com.example.vinilos.models.Album

class CacheManager(context: Context) {
    companion object{
        private var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var albums: HashMap<Int, Album> = hashMapOf<Int, Album>()
    fun addAlbums(albumId: Int, albumParam: Album){
        if (albums.containsKey(albumId)){
            albums[albumId] = albumParam
        }
    }
    fun getAlbums(albumId: Int) : Album? {
        return if (albums.containsKey(albumId)) albums[albumId]!! else null
    }
}