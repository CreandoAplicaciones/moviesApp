package com.navega.entre.moviapp.repository

import com.navega.entre.moviapp.data.model.MovieList

interface MovieRepository {

    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRateMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}