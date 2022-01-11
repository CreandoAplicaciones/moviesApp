package com.navega.entre.moviapp.repository

import com.navega.entre.moviapp.core.internetCheck
import com.navega.entre.moviapp.data.local.LocalMoviesDataSource
import com.navega.entre.moviapp.data.model.MovieList
import com.navega.entre.moviapp.data.model.toMovieEntity
import com.navega.entre.moviapp.data.remote.RemoteMovieDataSource
import javax.sql.DataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMoviesDataSource
) : MovieRepository {


    override suspend fun getUpcomingMovies(): MovieList {
        return if (internetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRateMovies(): MovieList {
        return if (internetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRateMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRateMovies()
        } else {
            dataSourceLocal.getTopRateMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (internetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }

}
