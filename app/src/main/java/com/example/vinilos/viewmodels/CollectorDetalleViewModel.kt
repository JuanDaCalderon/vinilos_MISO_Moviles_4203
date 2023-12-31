package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Artista
import com.example.vinilos.models.Collector
import com.example.vinilos.repositories.DetalleCollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorDetalleViewModel(application: Application, collectorId: Int) :  AndroidViewModel(application) {

    private val artistasRepository = DetalleCollectorRepository(application)

    private val _artistas = MutableLiveData<List<Artista>>()

    private val _collectors = MutableLiveData<Collector>()

    val artistas: LiveData<List<Artista>>
        get() = _artistas

    val collectors: LiveData<Collector>
        get() = _collectors

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = collectorId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = artistasRepository.refreshData(id)
                    _artistas.postValue(data)

                    var data2 = artistasRepository.bringCollector(id)
                    _collectors.postValue(data2)

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

    class Factory(val app: Application,  val collectorId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorDetalleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorDetalleViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
