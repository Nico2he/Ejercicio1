package com.example.ejercicio1.ui.comic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ejercicio1.R
import com.example.ejercicio1.databinding.FragmentComicBinding
import com.example.ejercicio1.ui.comicDetail.ComicDetailFragment
import com.example.ejercicio1.ui.edit.EditComicFragment

class ComicFragment : Fragment(R.layout.fragment_comic) {

    private val viewModel: ComicViewModel by viewModels()
    private lateinit var binding: FragmentComicBinding
    private val adapter = Adapter(){ comic -> viewModel.navigateTo(comic)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentComicBinding.bind(view).apply {
            recycler.adapter = adapter
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) VISIBLE else GONE
            state.comics?.let {
                adapter.comics = state.comics
                adapter.notifyDataSetChanged()
            }

            state.navigateTo?.let {
                findNavController().navigate(
                    R.id.action_comicFragment_to_comicDetailFragment,
                    bundleOf(ComicDetailFragment.EXTRA_COMIC to it)
                )
                viewModel.onNavigateDone()
            }

            state.navigateToCreate?.let{
                if (it) {
                    findNavController().navigate(
                        R.id.action_comicFragment_to_createComicFragment,
                    )
                    viewModel.navigateToCreateDone()
                }
            }

        }

        binding.addButton.setOnClickListener {
            viewModel.navigateToCreate()
        }

        binding.personajesButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_comicFragment_to_personajeFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("ComicFragment", "onDestroy")
    }
}