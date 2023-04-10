package com.example.ejercicio1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contacto(val nombre: String, val foto: String, val numero: String, val correo: String) :
    Parcelable {
}