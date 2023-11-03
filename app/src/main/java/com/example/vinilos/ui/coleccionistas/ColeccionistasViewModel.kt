package com.example.vinilos.ui.coleccionistas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColeccionistasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is coleccionista Fragment"
    }
    val text: LiveData<String> = _text
}