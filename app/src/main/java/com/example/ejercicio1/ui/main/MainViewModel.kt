package com.example.ejercicio1.ui.main

import androidx.lifecycle.*
import com.example.ejercicio1.model.Contacto
import com.example.ejercicio1.model.DbFirestore
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            DbFirestore.getAllObservable().observeForever {
                _state.value = _state.value?.copy(loading = false, contactos = it)
            }
        }

    }

    private suspend fun requestContactos(): List<Contacto>  = DbFirestore.getAll()

    fun navigateTo(contacto: Contacto) {
        _state.value = _state.value?.copy(navigateTo = contacto)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    fun navigateToCreate() {
        _state.value = _state.value?.copy(navigateToCreate = true)
    }

    fun navigateToCreateDone() {
        _state.value = _state.value?.copy(navigateToCreate = false)
    }

    data class UiState(
        val loading: Boolean = false,
        val contactos: List<Contacto>? = null,
        val navigateTo: Contacto? = null,
        val navigateToCreate: Boolean = false
    )

}