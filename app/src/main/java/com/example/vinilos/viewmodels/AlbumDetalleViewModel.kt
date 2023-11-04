package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.modelos.Album
import com.example.vinilos.repositories.AlbumDetalleRepository


class AlbumDetalleViewModel(application: Application, albumId: Int) :  AndroidViewModel(application) {
    private val detalleAlbumsRepository = AlbumDetalleRepository(application)

    private val _album = MutableLiveData<Album>()

    val albums: MutableLiveData<Album>
        get() = _album


    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        detalleAlbumsRepository.refreshData(id, {
            _album.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetalleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetalleViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}