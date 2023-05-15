package com.example.ejercicio1.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1.model.Contacto
import com.example.ejercicio1.model.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateContactoViewModel: ViewModel() {

    fun createContacto(contacto: Contacto){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createContacto(contacto)
        }

    }
}