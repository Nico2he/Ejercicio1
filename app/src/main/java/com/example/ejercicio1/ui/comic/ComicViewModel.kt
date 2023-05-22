package com.example.ejercicio1.ui.comic

import androidx.lifecycle.*
import com.example.ejercicio1.model.Comic
import com.example.ejercicio1.model.DbFirestore
import kotlinx.coroutines.*

class ComicViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            DbFirestore.getAllObservable().observeForever {
                _state.value = _state.value?.copy(loading = false, comics = it)
            }
        }

    }

    private suspend fun requestComics(): List<Comic>  = DbFirestore.getAll()

    fun navigateTo(comic: Comic) {
        _state.value = _state.value?.copy(navigateTo = comic)
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
        val comics: List<Comic>? = null,
        val navigateTo: Comic? = null,
        val navigateToCreate: Boolean = false
    )

}