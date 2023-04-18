package com.example.ejercicio1.model

import com.example.ejercicio1.Contacto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ContactosProvider {
    suspend fun getContactos(): List<Contacto> = withContext(Dispatchers.IO) {
        Thread.sleep(2000)
        listOf(
            Contacto("Pablo Rodríguez", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "123456789", "pablo@gmail.com"),
            Contacto("Pipo Masterclass", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "987654321", "pipomaster@gmail.com"),
            Contacto("Luis Star", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "132465798", "luiskiko@gmail.com")
        )
    }
}
