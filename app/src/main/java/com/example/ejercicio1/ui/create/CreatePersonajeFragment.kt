package com.example.ejercicio1.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentCreatePersonajeBinding
import com.example.ejercicio1.model.Personaje

class CreatePersonajeFragment : Fragment(R.layout.fragment_create_personaje) {
    private val viewModel: CreatePersonajeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreatePersonajeBinding.bind(view)

        binding.button.setOnClickListener {
            val personaje = Personaje(
                binding.editTextTextPersonajeNombre.text.toString(),
                binding.editTextTextPersonajeFoto.text.toString(),
                binding.editTextTextPersonajeSuperpoder.text.toString(),
                binding.editTextTextPersonajeEdad.text.toString(),
                binding.editTextTextPersonajeHistoria.text.toString(),
            )
            viewModel.createPersonaje(personaje)
            findNavController().navigate(
                R.id.action_createPersonajeFragment_to_personajeFragment)
        }
    }
}