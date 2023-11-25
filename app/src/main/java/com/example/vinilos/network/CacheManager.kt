package com.example.vinilos.network

import android.content.Context
import com.example.vinilos.models.Album
import com.example.vinilos.models.Artista
import com.example.vinilos.models.Collector

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
    private var artistas: HashMap<Int, Artista> = hashMapOf<Int, Artista>()
    private var collectors: HashMap<Int, Collector> = hashMapOf<Int, Collector>()
    fun addAlbums(albumId: Int, albumParam: Album){
        if (!albums.containsKey(albumId)){
            albums[albumId] = albumParam
        }
    }
    fun getAlbums(albumId: Int) : Album? {
        return if (albums.containsKey(albumId)) albums[albumId]!! else null
    }

    fun addArtistas(artistaId: Int, artistaParam: Artista){
        if (!artistas.containsKey(artistaId)){
            artistas[artistaId] = artistaParam
        }
    }
    fun getArtistas(artistaId: Int) : Artista? {
        return if (artistas.containsKey(artistaId)) artistas[artistaId]!! else null
    }

    fun addCollectors(collectorId: Int, collectorParam: Collector){
        if (!collectors.containsKey(collectorId)){
            collectors[collectorId] = collectorParam
        }
    }
    fun getCollectors(collectorId: Int) : Collector? {
        return if (collectors.containsKey(collectorId)) collectors[collectorId]!! else null
    }

}