package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Artista
import com.example.vinilos.network.NetworkServiceAdapter

class ArtistaRepository (val application: Application){
    suspend fun refreshData(): List<Artista> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getArtistas()
    }
}