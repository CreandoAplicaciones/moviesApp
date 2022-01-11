package com.navega.entre.moviapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.navega.entre.moviapp.core.resource
import com.navega.entre.moviapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo:MovieRepository):ViewModel() {


    //we pass 3 methods to one method

    fun fetchMainScreenMovies()= liveData(viewModelScope.coroutineContext + Dispatchers.Main){

        emit(resource.Loanding())

        try {
            emit(resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRateMovies(),repo.getPopularMovies())))

        }catch (e:Exception){
            emit(resource.Failure(e))


        }
    }



}
class MovieViewModelFactory (private val repo: MovieRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}