package com.example.ejercicio1.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1.model.DbFirestore
import com.example.ejercicio1.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreatePersonajeViewModel: ViewModel() {

    fun createPersonaje(personaje: Personaje){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createPersonaje(personaje)
        }

    }
}