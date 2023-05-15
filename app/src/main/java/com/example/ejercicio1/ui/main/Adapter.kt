package com.example.ejercicio1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio1.databinding.ViewContactItemBinding
import com.example.ejercicio1.model.Contacto

class Adapter(val listener: (Contacto) -> Unit):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var contactos = emptyList<Contacto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewContactItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = contactos[position]
        holder.bind(contacto)

        holder.itemView.setOnClickListener {
            listener(contacto)
        }

    }

    override fun getItemCount(): Int = contactos.size

    class ViewHolder(private val binding: ViewContactItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(contacto: Contacto) {

            binding.nombre.text = contacto.nombre
            binding.numero.text = contacto.numero
            binding.correo.text = contacto.correo

            Glide
                .with(binding.root.context)
                .load(contacto.foto)
                .into(binding.foto)

        }

    }

}