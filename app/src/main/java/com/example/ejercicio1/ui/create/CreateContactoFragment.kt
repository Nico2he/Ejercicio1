package com.example.ejercicio1.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentCreateContactoBinding
import com.example.ejercicio1.model.Contacto


class CreateContactoFragment : Fragment(R.layout.fragment_create_contacto) {
    private val viewModel: CreateContactoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateContactoBinding.bind(view)

        binding.button.setOnClickListener {
            val contacto = Contacto(binding.editTextTextPersonName.text.toString())
            viewModel.createContacto(contacto)
            findNavController().navigate(
                R.id.action_createContactoFragment_to_mainFragment)
        }
    }
}