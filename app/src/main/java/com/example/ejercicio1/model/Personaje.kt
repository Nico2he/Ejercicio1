package com.example.ejercicio1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personaje(
    val nombre: String = "",
    val foto: String = "",
    val superpoder: String = "",
    val edad: String = "",
    val historia: String = ""
): Parcelable {
}