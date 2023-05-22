package com.example.ejercicio1.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DbFirestore {
    const val COLLECTION_COMICS = "comics"
    suspend fun getAll(): List<Comic> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
            .get()
            .await()
        val comics = mutableListOf<Comic>()
        for (documentSnapshot in snapshot){
            val comic = documentSnapshot.toObject(Comic::class.java)
            comics.add(comic)
        }
        return comics
    }

    suspend fun createComic(comic: Comic){
        FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
            .add(comic)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_COMICS, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_COMICS, it.toString())
            }

    }

    fun borraComic(comic: Comic) {
        FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
            .whereEqualTo("titulo", comic.titulo)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_COMICS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_COMICS, it.toString())
                        }
                }
            }
    }

    fun modificaTitulo(comic: Comic?, titulo: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
            .whereEqualTo("titulo", titulo)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
                        .document(id)
                        .update("titulo", comic?.titulo)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_COMICS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_COMICS, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Comic>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Comic>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_COMICS)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Comic>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Comic::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

}