package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter

class CrearAlbumRepository (val application: Application) {
    fun refreshData(callback: (List<Album>) -> Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).postAlbum({
            callback(it)
        },
            onError
        )
    }
}