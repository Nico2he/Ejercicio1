package com.example.ejercicio1.ui.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.ViewComicItemBinding
import com.example.ejercicio1.model.Comic
import com.example.ejercicio1.model.DbFirestore
import com.example.ejercicio1.ui.edit.EditComicFragment

class Adapter(val listener: (Comic) -> Unit):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var comics = emptyList<Comic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewComicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.bind(comic)

        holder.itemView.setOnClickListener {
            listener(comic)
        }

    }

    override fun getItemCount(): Int = comics.size

    class ViewHolder(private val binding: ViewComicItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {

            binding.titulo.text = comic.titulo
            binding.anyoEstreno.text = comic.anyoEstreno
            binding.borrarComicButton.setOnClickListener{
                DbFirestore.borraComic(comic)
            }

            Glide
                .with(binding.root.context)
                .load(comic.portada)
                .into(binding.portada)

        }

    }

}