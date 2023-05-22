package com.example.ejercicio1.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentCreateComicBinding
import com.example.ejercicio1.model.Comic

class CreateComicFragment : Fragment(R.layout.fragment_create_comic) {
    private val viewModel: CreateComicViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateComicBinding.bind(view)

        binding.button.setOnClickListener {
            val comic = Comic(binding.editTextTextComicTitle.text.toString())
            viewModel.createComic(comic)
            findNavController().navigate(
                R.id.action_createComicFragment_to_comicFragment)
        }
    }
}