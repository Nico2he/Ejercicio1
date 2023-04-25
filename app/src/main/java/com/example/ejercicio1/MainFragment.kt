package com.example.ejercicio1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.databinding.ActivityMainBinding
import com.example.ejercicio1.model.ContactosProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.activity_main) {
    private val adapter = Adapter(){contacto -> navigateTo(contacto)}

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = getString(R.string.app_name)

        viewModel.progressVisible.observe(viewLifecycleOwner) { visible ->
            binding.progress.visibility = if (visible) VISIBLE else GONE
        }

        viewModel.contactos.observe(viewLifecycleOwner) {
            adapter.contactos = it
            adapter.notifyDataSetChanged()

        }

        object : ContactoClickedListener {

            override fun onContactoClicked(contacto: Contacto) {

                findNavController().navigate(
                    R.id.action_mainFragment_to_contactoFragment,
                    bundleOf(ContactoFragment.EXTRA_CONTACTO to contacto)
                )

            }

        }

    }

    private fun navigateTo(contacto: Contacto) {
        findNavController().navigate(
            R.id.action_mainFragment_to_contactoFragment,
            bundleOf(ContactoFragment.EXTRA_CONTACTO to contacto)
        )

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy")
    }

}