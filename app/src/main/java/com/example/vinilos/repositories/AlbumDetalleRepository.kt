package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.modelos.Album
import com.example.vinilos.network.NetworkServiceAdapter

class AlbumDetalleRepository (val application: Application) {

    fun refreshData(albumId: Int, callback: (Album)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbum(albumId,{
            callback(it)
        },
            onError
        )
    }
}