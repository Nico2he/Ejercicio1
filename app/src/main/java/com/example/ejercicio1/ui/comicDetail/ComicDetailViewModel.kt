package com.example.ejercicio1.ui.comicDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicio1.model.Comic


class ComicDetailViewModel(comic: Comic): ViewModel() {
    private val _comic = MutableLiveData(comic)
    val comic: LiveData<Comic> get() = _comic
}

@Suppress("UNCHECKED_CAST")
class ComicDetailViewModelFactory(private val comic: Comic): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComicDetailViewModel(comic) as T
    }

}