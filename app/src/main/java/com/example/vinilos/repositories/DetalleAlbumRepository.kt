package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.models.Album
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleAlbumRepository (val application: Application){
    suspend fun refreshData(albumId: Int): Album {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getAlbums(albumId)

        if(potentialResp != null) {
            Log.d("Cache decision", "return ${potentialResp} element from cache")
            return potentialResp
        }
        else {
            Log.d("Cache decision", "get from network")
            var album = NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
            CacheManager.getInstance(application.applicationContext).addAlbums(albumId, album)
            return album
        }
    }
}