package com.example.ejercicio1

import androidx.lifecycle.*
import com.example.ejercicio1.model.ContactosProvider
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val contactos =  withContext(Dispatchers.IO){ContactosProvider.getContactos()}
            _state.value = _state.value?.copy(loading = false, contactos = contactos)
        }

    }

    fun navigateTo(contacto: Contacto) {
        _state.value = _state.value?.copy(navigateTo = contacto)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val contactos: List<Contacto>? = null,
        val navigateTo: Contacto? = null
    )

}