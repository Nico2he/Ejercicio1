package com.example.ejercicio1.ui.personaje

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentPersonajeBinding
import com.example.ejercicio1.ui.personajeDetail.PersonajeDetailFragment

class PersonajeFragment : Fragment(R.layout.fragment_personaje) {

    private val viewModel: PersonajeViewModel by viewModels()
    private lateinit var binding: FragmentPersonajeBinding
    private val adapter = AdapterPersonaje(){ personaje -> viewModel.navigateTo(personaje)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonajeBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.personajes?.let {
                adapter.personajes = state.personajes
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_personajeFragment_to_personajeDetailFragment,
                    bundleOf(PersonajeDetailFragment.EXTRA_PERSONAJE to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_personajeFragment_to_createPersonajeFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.addButton.setOnClickListener {
            viewModel.navigateToCreate()
        }

        binding.comicsButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_personajeFragment_to_comicFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("PersonajeFragment", "onDestroy")
    }
}