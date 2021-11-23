package com.diegolima.TheMoviesTMDb.ui.populardetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegolima.TheMoviesTMDb.core.network.NetworkResponse
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponse
import com.diegolima.TheMoviesTMDb.domain.model.Result
import com.diegolima.TheMoviesTMDb.domain.network.MovieRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: MovieRepositoryInterface
): ViewModel(){
    private val moviesPopular = MutableLiveData<NetworkResponse<MovieResponse>>()

    val moviesListPopular: LiveData<NetworkResponse<MovieResponse>>
        get() = moviesPopular

    var movie: Result? = null

    fun getMoviesPopular(){
        moviesPopular.value = NetworkResponse.loading(null)

        viewModelScope.launch {
            val response = repository.getMoviesPopular()
            moviesPopular.value = response
        }
    }

}