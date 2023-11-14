package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.models.Artista
import com.example.vinilos.models.Collector
import com.example.vinilos.network.CacheManager
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleCollectorRepository (val application: Application){
    suspend fun refreshData(collectorId: Int): Collector {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getCollectors(collectorId)
        if(potentialResp != null) {
            Log.d("CACHE DECISION", "return $potentialResp element from cache")
            return potentialResp
        }
        else {
            Log.d("CACHE DECISION", "get from network")
            var coleccionista = NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
            CacheManager.getInstance(application.applicationContext).addCollectors(collectorId, coleccionista)
            return coleccionista
        }
    }
}