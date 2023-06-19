package com.example.ejercicio1.ui.edit

import androidx.lifecycle.*
import com.example.ejercicio1.model.Comic
import com.example.ejercicio1.model.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditComicViewModel(comic: Comic): ViewModel() {
    private val _comic = MutableLiveData(comic)
    val comic: LiveData<Comic> get() = _comic

    fun createComic(comic: Comic){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createComic(comic)
        }

    }
}

@Suppress("UNCHECKED_CAST")
class EditComicViewModelFactory(private val comic: Comic): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditComicViewModel(comic) as T
    }

}