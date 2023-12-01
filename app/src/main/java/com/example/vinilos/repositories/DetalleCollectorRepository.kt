package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Artista
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter

class DetalleCollectorRepository (val application: Application){
    suspend fun refreshData(collectorId:Int): List<Artista> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getArtistasByCollector(collectorId)
    }

    suspend fun bringCollector(collectorId:Int): Collector {
        return NetworkServiceAdapter.getInstance(application).getCollector(collectorId)
    }
}

