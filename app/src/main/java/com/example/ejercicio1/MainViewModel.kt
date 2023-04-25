package com.example.ejercicio1

import android.view.View
import androidx.lifecycle.*
import com.example.ejercicio1.model.ContactosProvider
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _progressVisible = MutableLiveData(false)
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _contactos = MutableLiveData<List<Contacto>>(emptyList())
    val contactos: LiveData<List<Contacto>> get() = _contactos

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _progressVisible.value = true
            _contactos.value =  withContext(Dispatchers.IO){ContactosProvider.getContactos()}
            _progressVisible.value = false
        }
    }


}