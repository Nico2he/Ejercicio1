package com.example.ejercicio1.ui.personajeDetail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ejercicio1.R
import com.example.ejercicio1.model.Personaje

class PersonajeDetailFragment : Fragment(R.layout.fragment_personaje_detail) {
    private val viewModel: PersonajeDetailViewModel by viewModels {
        PersonajeDetailViewModelFactory(arguments?.getParcelable<Personaje>(EXTRA_PERSONAJE)!!)
    }
    companion object {
        const val EXTRA_PERSONAJE = "PersonajeDetailFragment:Personaje"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fotoPersonaje = view.findViewById<ImageView>(R.id.fotoPersonaje)
        val nombrePersonaje = view.findViewById<TextView>(R.id.nombrePersonaje)
        val superpoderPersonaje = view.findViewById<TextView>(R.id.superpoderPersonaje)
        val edadPersonaje = view.findViewById<TextView>(R.id.edadPersonaje)
        val historiaPersonaje = view.findViewById<TextView>(R.id.historiaPersonaje)

        viewModel.personaje.observe(viewLifecycleOwner){ personaje: Personaje ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = personaje.nombre
            Glide.with(this).load(personaje.foto).into(fotoPersonaje)
            nombrePersonaje.text = personaje.nombre
            superpoderPersonaje.text = personaje.superpoder
            edadPersonaje.text = personaje.edad
            historiaPersonaje.text = personaje.historia

        }

    }

}