package com.example.ejercicio1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ejercicio1.databinding.ActivityMainBinding

class MainFragment : Fragment(R.layout.activity_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.bind(view)

        val fragment = ContactoFragment()
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .setReorderingAllowed(true)
            .commit()

        binding.recycler.adapter = Adapter(
            listOf(
                Contacto("Pablo Rodríguez", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "123456789", "pablo@gmail.com"),
                Contacto("Pipo Masterclass", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "987654321", "pipomaster@gmail.com"),
                Contacto("Luis Star", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "132465798", "luiskiko@gmail.com")
            ),

            object : ContactoClickedListener {

                override fun onContactoClicked(contacto: Contacto) {

                    fragment.arguments = bundleOf(ContactoFragment.EXTRA_CONTACTO to contacto)

                }

            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy")
    }

}