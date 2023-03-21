package com.example.ejercicio1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
                Contacto("Pablo Rodríguez", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "123456789", "pablo@gmail.com"),
                Contacto("Pipo Masterclass", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "987654321", "pipomaster@gmail.com"),
                Contacto("Luis Star", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "132465798", "luiskiko@gmail.com")
            ),

            object : ContactoClickedListener {

                override fun onContactoClicked(contacto: Contacto) {

                    setContentView(R.layout.contact)

                    findViewById<TextView>(R.id.nombreContacto).text = contacto.nombre

                    findViewById<ImageView>(R.id.fotoContacto).setImageResource(R.drawable.ic_launcher_foreground)

                    findViewById<Button>(R.id.llamar).setOnClickListener { call(contacto.numero) }

                    findViewById<Button>(R.id.email).setOnClickListener { mail(contacto.correo) }

                }

            }
        )
    }

    fun call(telefono: String) {

        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent)

    }

    fun mail(correo: String) {

        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", correo, null))
        startActivity(intent)

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("MainActivity", "onDestroy")
    }

}