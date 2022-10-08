package com.example.ejercicio1

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = Adapter(
            listOf(
                Contacto("Pablo Rodríguez", "https://www.pngplay.com/wp-content/uploads/2/Cool-Android-Background-PNG-Image.png", "123456789", "pablo@gmail.com"),
                Contacto("Pipo Masterclass", "https://www.pngplay.com/wp-content/uploads/2/Cool-Android-Background-PNG-Image.png", "987654321", "pipomaster@gmail.com"),
                Contacto("Luis Star", "https://www.pngplay.com/wp-content/uploads/2/Cool-Android-Background-PNG-Image.png", "132465798", "luiskiko@gmail.com")
            ),

            object : ContactoClickedListener {

                override fun onContactoClicked(contacto: Contacto) {

                    setContentView(R.layout.contact)

                    findViewById<TextView>(R.id.nombreContacto).text = contacto.nombre

                    findViewById<ImageView>(R.id.fotoContacto).setImageResource(R.drawable.ic_launcher_foreground)

                }

            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy")
    }

}