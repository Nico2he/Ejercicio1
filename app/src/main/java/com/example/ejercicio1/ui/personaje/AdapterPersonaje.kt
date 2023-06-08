package com.example.ejercicio1.ui.personaje

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio1.databinding.ViewPersonajeItemBinding
import com.example.ejercicio1.model.DbFirestore
import com.example.ejercicio1.model.Personaje

class AdapterPersonaje(val listener: (Personaje) -> Unit):
    RecyclerView.Adapter<AdapterPersonaje.ViewHolder>() {

    var personajes = emptyList<Personaje>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewPersonajeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.bind(personaje)

        holder.itemView.setOnClickListener {
            listener(personaje)
        }

    }

    override fun getItemCount(): Int = personajes.size

    class ViewHolder(private val binding: ViewPersonajeItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(personaje: Personaje) {

            binding.nombrePersonaje.text = personaje.nombre
            binding.borrarPersonajeButton.setOnClickListener{
                DbFirestore.borraPersonaje(personaje)
            }

            Glide
                .with(binding.root.context)
                .load(personaje.foto)
                .into(binding.foto)

        }

    }

}