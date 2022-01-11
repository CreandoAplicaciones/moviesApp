package com.navega.entre.moviapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.navega.entre.moviapp.R
import com.navega.entre.moviapp.core.TopRateConcatAdapter
import com.navega.entre.moviapp.core.UpcomingConcatAdapter
import com.navega.entre.moviapp.core.resource
import com.navega.entre.moviapp.data.local.AppDatabase
import com.navega.entre.moviapp.data.local.LocalMoviesDataSource
import com.navega.entre.moviapp.data.model.Movie
import com.navega.entre.moviapp.data.remote.RemoteMovieDataSource
import com.navega.entre.moviapp.databinding.FragmentMovieBinding
import com.navega.entre.moviapp.presentation.MovieViewModel
import com.navega.entre.moviapp.presentation.MovieViewModelFactory
import com.navega.entre.moviapp.repository.MovieRepositoryImpl
import com.navega.entre.moviapp.repository.RetrofitClient
import com.navega.entre.moviapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.navega.entre.moviapp.ui.movie.adapters.movieAdapter


class movieFragment : Fragment(R.layout.fragment_movie), movieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMoviesDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

//observe at viewModel and change only when viewModel sends another emit

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is resource.Loanding -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                movieAdapter(
                                    result.data.first.results,
                                    this@movieFragment
                                )
                            )
                        )
                        addAdapter(
                            0,
                            TopRateConcatAdapter(
                                movieAdapter(
                                    result.data.second.results,
                                    this@movieFragment
                                )
                            )
                        )
                        addAdapter(
                            0,
                            PopularConcatAdapter(
                                movieAdapter(
                                    result.data.third.results,
                                    this@movieFragment
                                )
                            )
                        )

                        binding.rvMovies.adapter = concatAdapter
                    }

                }
                is resource.Failure -> {
                    binding.progressBar.visibility = View.GONE

                    Log.d("Error", "${result.exception}")
                }
            }


        })


    }

    override fun onMovieClick(movie: Movie) {
        val action = movieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.title,
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.original_language,
            movie.release_data

        )
        findNavController().navigate(action)
    }


}