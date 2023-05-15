package com.example.ejercicio1.model

object ContactosProvider {
    fun getContactos(): List<Contacto> {
        Thread.sleep(2000)
        return listOf(
            Contacto("Pablo Rodríguez", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "123456789", "pablo@gmail.com"),
            Contacto("Pipo Masterclass", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "987654321", "pipomaster@gmail.com"),
            Contacto("Luis Star", "https://static.wikia.nocookie.net/roblox/images/f/fe/Womanface.png/revision/latest/top-crop/width/360/height/450?cb=20211031043454", "132465798", "luiskiko@gmail.com")
        )
    }
}
