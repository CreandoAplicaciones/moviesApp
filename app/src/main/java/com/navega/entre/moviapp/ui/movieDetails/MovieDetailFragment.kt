package com.navega.entre.moviapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.navega.entre.moviapp.R
import com.navega.entre.moviapp.databinding.FragmentMovieDetailBinding


class movieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<movieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImagenUrl}").centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImagenUrl}").centerCrop().into(binding.imgBackground)
        binding.txtDescription.text=args.overView
        binding.txtLanguage.text="Language ${args.language}"
        binding.txtTitle.text=args.title
        binding.txtRelased.text="Relased ${args.relaseDate}"
        binding.txtRating.text="${args.votoAverage}(${args.voteCount} Reviews)"



    }
}