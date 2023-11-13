package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.models.Artista
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleArtistaRepository (val application: Application){
    suspend fun refreshData(artistaId: Int): Artista {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getArtistas(artistaId)
        if(potentialResp != null) {
            Log.d("CACHE DECISION", "return $potentialResp element from cache")
            return potentialResp
        }
        else {
            Log.d("CACHE DECISION", "get from network")
            var artista = NetworkServiceAdapter.getInstance(application).getArtista(artistaId)
            CacheManager.getInstance(application.applicationContext).addArtistas(artistaId, artista)
            return artista
        }
    }
}