package com.example.ejercicio1.ui.comicDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ejercicio1.R
import com.example.ejercicio1.model.Comic

class ComicDetailFragment : Fragment(R.layout.fragment_comic_detail) {
    private val viewModel: ComicDetailViewModel by viewModels {
        ComicDetailViewModelFactory(arguments?.getParcelable<Comic>(EXTRA_COMIC)!!)
    }
    companion object {
        const val EXTRA_COMIC = "ComicDetailFragment:Comic"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tituloComic = view.findViewById<TextView>(R.id.tituloComic)
        val portada = view.findViewById<ImageView>(R.id.portadaComic)

        viewModel.comic.observe(viewLifecycleOwner){ comic: Comic ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = comic.titulo
            tituloComic.text = comic.titulo
            portada.setImageResource(R.drawable.ic_launcher_foreground)
            //botonLlamar.setOnClickListener { call(comic.numero) }

        }

    }

    /*
    fun call(telefono: String) {

        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))
        startActivity(intent)

    }

    fun mail(correo: String) {

        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", correo, null))
        startActivity(intent)

    }
    */
}