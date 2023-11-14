package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Artista
import com.example.vinilos.repositories.DetalleArtistaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistaDetalleViewModel(application: Application, artistaId: Int) :  AndroidViewModel(application) {

    private val artistaDetalleRepository = DetalleArtistaRepository(application)

    private val _artista = MutableLiveData<Artista>()

    val artista: LiveData<Artista>
        get() = _artista

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = artistaId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    val data = artistaDetalleRepository.refreshData(id)
                    _artista.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val artistaId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistaDetalleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistaDetalleViewModel(app, artistaId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
