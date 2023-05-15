package com.example.ejercicio1.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DbFirestore {
    const val COLLECTION_CONTACTOS = "contactos"
    suspend fun getAll(): List<Contacto> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
            .get()
            .await()
        val contactos = mutableListOf<Contacto>()
        for (documentSnapshot in snapshot){
            val contacto = documentSnapshot.toObject(Contacto::class.java)
            contactos.add(contacto)
        }
        return contactos
    }

    suspend fun createContacto(contacto: Contacto){
        FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
            .add(contacto)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(COLLECTION_CONTACTOS, it.result.id)
                }
            }
            .addOnFailureListener {
                Log.e(COLLECTION_CONTACTOS, it.toString())
            }

    }

    fun borraContacto(contacto: Contacto) {
        FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
            .whereEqualTo("nombre", contacto.nombre)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
                        .document(id)
                        .delete()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_CONTACTOS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_CONTACTOS, it.toString())
                        }
                }
            }
    }

    fun modificaNombre(contacto: Contacto?, nombre: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
            .whereEqualTo("nombre", nombre)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val id = it.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
                        .document(id)
                        .update("nombre", contacto?.nombre)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(COLLECTION_CONTACTOS, id)
                            }
                        }
                        .addOnFailureListener {
                            Log.e(COLLECTION_CONTACTOS, it.toString())
                        }
                }
            }
    }

    suspend fun getAllObservable(): LiveData<MutableList<Contacto>> {

        return withContext(Dispatchers.IO) {
            val mutableData = MutableLiveData<MutableList<Contacto>>()
            FirebaseFirestore.getInstance().collection(COLLECTION_CONTACTOS)
                .addSnapshotListener { snapshot, e ->
                    var listas = mutableListOf<Contacto>()
                    if (snapshot != null) {
                        listas = snapshot.toObjects(Contacto::class.java)
                    }
                    mutableData.value = listas
                }

            return@withContext mutableData
        }
    }

}