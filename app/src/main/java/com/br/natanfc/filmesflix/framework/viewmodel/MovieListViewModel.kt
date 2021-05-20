package com.br.natanfc.filmesflix.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.natanfc.filmesflix.data.MovieRepository
import com.br.natanfc.filmesflix.domain.Movie
import com.br.natanfc.filmesflix.framework.api.MovieRestApiTask
import com.br.natanfc.filmesflix.implementation.MovieDataSourceImplementation
import com.br.natanfc.filmesflix.usecase.MoviesListUseCase

class MovieListViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieListViewModel"
    }


    private val movieRestApiTask = MovieRestApiTask()
    private val movieDataSource = MovieDataSourceImplementation(movieRestApiTask)
    private val movieRepository = MovieRepository(movieDataSource)
    private val moviesListUseCase = MoviesListUseCase(movieRepository)

    private var _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun init() {
        getAllMovies()
    }

    private fun getAllMovies() {
        Thread {
            try {
                _moviesList.postValue(moviesListUseCase.invoke())
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }.start()
    }
}