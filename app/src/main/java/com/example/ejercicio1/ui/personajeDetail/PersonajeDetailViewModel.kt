package com.example.ejercicio1.ui.personajeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio1.model.Personaje

class PersonajeDetailViewModel(personaje: Personaje): ViewModel() {
    private val _personaje = MutableLiveData(personaje)
    val personaje: LiveData<Personaje> get() = _personaje
}

@Suppress("UNCHECKED_CAST")
class PersonajeDetailViewModelFactory(private val personaje: Personaje): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonajeDetailViewModel(personaje) as T
    }

}