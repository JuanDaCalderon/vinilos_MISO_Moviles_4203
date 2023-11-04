package com.example.vinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Artistas View Model"
    }
    val text: LiveData<String> = _text
}