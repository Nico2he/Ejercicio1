package com.example.ejercicio1.ui.contacto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio1.model.Contacto

class ContactoViewModel(contacto: Contacto): ViewModel() {
    private val _contacto = MutableLiveData(contacto)
    val contacto: LiveData<Contacto> get() = _contacto
}

@Suppress("UNCHECKED_CAST")
class ContactoViewModelFactory(private val contacto: Contacto): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactoViewModel(contacto) as T
    }

}