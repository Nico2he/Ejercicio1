package com.example.ejercicio1.ui.personaje

import androidx.lifecycle.*
import com.example.ejercicio1.model.DbFirestore
import com.example.ejercicio1.model.Personaje
import kotlinx.coroutines.*

class PersonajeViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            DbFirestore.getAllObservablePersonajes().observeForever {
                _state.value = _state.value?.copy(loading = false, personajes = it)
            }
        }

    }

    private suspend fun requestPersonajes(): List<Personaje>  = DbFirestore.getAllPersonajes()

    fun navigateTo(personaje: Personaje) {
        _state.value = _state.value?.copy(navigateTo = personaje)
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
        val personajes: List<Personaje>? = null,
        val navigateTo: Personaje? = null,
        val navigateToCreate: Boolean = false
    )

}