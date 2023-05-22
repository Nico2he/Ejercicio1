package com.example.ejercicio1.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio1.model.Comic
import com.example.ejercicio1.model.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateComicViewModel: ViewModel() {

    fun createComic(comic: Comic){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createComic(comic)
        }

    }
}