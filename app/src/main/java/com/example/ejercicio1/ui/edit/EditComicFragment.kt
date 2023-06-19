package com.example.ejercicio1.ui.edit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentEditComicBinding
import com.example.ejercicio1.model.Comic

class EditComicFragment : Fragment(R.layout.fragment_edit_comic) {
    private val viewModel: EditComicViewModel by viewModels{
        EditComicViewModelFactory(arguments?.getParcelable<Comic>(EXTRA_COMIC)!!)
    }
    companion object {
        const val EXTRA_COMIC = "EditComicFragment:Comic"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditComicBinding.bind(view)

        viewModel.comic.observe(viewLifecycleOwner) { comic: Comic ->
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "Editar " + comic.titulo
            binding.editComicTitle.setText(comic.titulo)

        }

        binding.buttonEdit.setOnClickListener {
            val comic = Comic(
                binding.editComicTitle.text.toString(),
                binding.editComicFrontpage.text.toString(),
                binding.editComicPremiereDate.text.toString(),
                binding.editComicPremiereYear.text.toString(),
                binding.editComicChapters.text.toString(),
                binding.editComicSynopsis.text.toString(),
                binding.editComicRating.text.toString(),
            )
            viewModel.createComic(comic)
            findNavController().navigate(
                R.id.action_editComicFragment_to_comicFragment)
        }
    }
}