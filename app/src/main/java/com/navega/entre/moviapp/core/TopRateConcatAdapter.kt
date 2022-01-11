package com.navega.entre.moviapp.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navega.entre.moviapp.databinding.TopRatedMovieRowBinding
import com.navega.entre.moviapp.ui.movie.adapters.movieAdapter

class TopRateConcatAdapter (private val moviesAdapter: movieAdapter) :
    RecyclerView.Adapter<baseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): baseConcatHolder<*> {
        val itemBinding =
            TopRatedMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: baseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: TopRatedMovieRowBinding) :
        baseConcatHolder<movieAdapter>(binding.root) {
        override fun bind(adapter: movieAdapter) {
            binding.rvTopRatedMovie.adapter = adapter

        }
    }
}