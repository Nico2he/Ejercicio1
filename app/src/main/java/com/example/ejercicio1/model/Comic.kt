package com.example.ejercicio1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val titulo: String = "",
    val portada: String = "",
    val fechaEstreno: String = "",
    val anyoEstreno: String = "",
    val capitulos: String = "",
    val sinopsis: String = "",
    val valoracion: String = ""
): Parcelable {
}