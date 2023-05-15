package com.example.ejercicio1.ui.main

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
import com.example.ejercicio1.databinding.FragmentMainBinding
import com.example.ejercicio1.ui.contacto.ContactoFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private val adapter = Adapter(){contacto -> viewModel.navigateTo(contacto)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.contactos?.let {
                adapter.contactos = state.contactos
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_mainFragment_to_contactoFragment,
                    bundleOf(ContactoFragment.EXTRA_CONTACTO to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_mainFragment_to_createContactoFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.fab.setOnClickListener {
            viewModel.navigateToCreate()
        }

        /*
        object : ContactoClickedListener {
            override fun onContactoClicked(contacto: Contacto) {
                findNavController().navigate(
                    R.id.action_mainFragment_to_contactoFragment,
                    bundleOf(ContactoFragment.EXTRA_CONTACTO to contacto)
                )
            }
        }
        */

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy")
    }
}