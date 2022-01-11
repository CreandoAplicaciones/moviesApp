package com.navega.entre.moviapp.data.local

import com.navega.entre.moviapp.application.AppConstants
import com.navega.entre.moviapp.data.model.MovieEntity
import com.navega.entre.moviapp.data.model.MovieList
import com.navega.entre.moviapp.data.model.toMovieList

class LocalMoviesDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRateMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }


    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}