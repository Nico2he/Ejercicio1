package com.example.ejercicio1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactoFragment : Fragment(R.layout.contact) {
    companion object {
        const val EXTRA_CONTACTO = "ContactoFragment:Contacto"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombreContacto = view.findViewById<TextView>(R.id.nombreContacto)
        val imagen = view.findViewById<ImageView>(R.id.fotoContacto)
        val botonLlamar = view.findViewById<Button>(R.id.llamar)
        val botonEmail = view.findViewById<Button>(R.id.email)

        val contacto = arguments?.getParcelable<Contacto>(EXTRA_CONTACTO)
        if (contacto != null) {
            (requireActivity() as AppCompatActivity)
                .supportActionBar?.title = contacto.nombre
            nombreContacto.text = contacto.nombre
            imagen.setImageResource(R.drawable.ic_launcher_foreground)
            botonLlamar.setOnClickListener { call(contacto.numero) }
            botonEmail.setOnClickListener { mail(contacto.correo) }

        }

    }

    fun call(telefono: String) {

        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent)

    }

    fun mail(correo: String) {

        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", correo, null))
        startActivity(intent)

    }
}