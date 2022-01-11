package com.navega.entre.moviapp.data.remote

import com.navega.entre.moviapp.application.AppConstants
import com.navega.entre.moviapp.data.model.MovieList
import com.navega.entre.moviapp.repository.WebService

class RemoteMovieDataSource (private val webService: WebService){

    suspend fun getUpcomingMovies():MovieList= webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRateMovies():MovieList= webService.getTopRateMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies():MovieList= webService.getPopularMovies(AppConstants.API_KEY)

}