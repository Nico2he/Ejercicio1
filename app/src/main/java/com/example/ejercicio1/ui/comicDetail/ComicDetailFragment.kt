package com.example.ejercicio1.ui.comicDetail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ejercicio1.R
import com.example.ejercicio1.model.Comic
import com.example.ejercicio1.model.DbFirestore
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val portadaComic = view.findViewById<ImageView>(R.id.portadaComic)
        val tituloComic = view.findViewById<TextView>(R.id.tituloComic)
        val fechaEstrenoComic = view.findViewById<TextView>(R.id.fechaEstrenoComic)
        val capitulosComic = view.findViewById<TextView>(R.id.capitulosComic)
        val sinopsisComic = view.findViewById<TextView>(R.id.sinopsisComic)
        val valoracionComic = view.findViewById<TextView>(R.id.valoracionComic)

        val editButton = view.findViewById<FloatingActionButton>(R.id.editButton)
        val editFechaEstrenoComic = view.findViewById<EditText>(R.id.editFechaEstrenoComic)
        val editCapitulosComic = view.findViewById<EditText>(R.id.editCapitulosComic)
        val editSinopsisComic = view.findViewById<EditText>(R.id.editSinopsisComic)
        val editValoracionComic = view.findViewById<EditText>(R.id.editValoracionComic)
        val editComicButton = view.findViewById<Button>(R.id.editComicButton)

        viewModel.comic.observe(viewLifecycleOwner){ comic: Comic ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = comic.titulo
            Glide.with(this).load(comic.portada).into(portadaComic)
            tituloComic.text = comic.titulo
            fechaEstrenoComic.text = comic.fechaEstreno
            capitulosComic.text = comic.capitulos
            sinopsisComic.text = comic.sinopsis
            valoracionComic.text = comic.valoracion + "/5 " + getString(R.string.Star)

            editFechaEstrenoComic.setText(comic.fechaEstreno)
            editCapitulosComic.setText(comic.capitulos)
            editSinopsisComic.setText(comic.sinopsis)
            editValoracionComic.setText(comic.valoracion)

            editButton.setOnClickListener {
                fechaEstrenoComic.visibility = View.GONE
                capitulosComic.visibility = View.GONE
                sinopsisComic.visibility = View.GONE
                valoracionComic.visibility = View.GONE

                editFechaEstrenoComic.visibility = View.VISIBLE
                editCapitulosComic.visibility = View.VISIBLE
                editSinopsisComic.visibility = View.VISIBLE
                editValoracionComic.visibility = View.VISIBLE
                editComicButton.visibility = View.VISIBLE
            }

            editComicButton.setOnClickListener{
                val comicEditado = Comic(
                    tituloComic.text.toString(),
                    "null",
                    editFechaEstrenoComic.text.toString(),
                    "null",
                    editCapitulosComic.text.toString(),
                    editSinopsisComic.text.toString(),
                    editValoracionComic.text.toString()

                )
                DbFirestore.editarComic(comicEditado, tituloComic.text.toString())
                findNavController().navigate(
                    R.id.action_comicDetailFragment_to_comicFragment)
            }

        }

    }

}