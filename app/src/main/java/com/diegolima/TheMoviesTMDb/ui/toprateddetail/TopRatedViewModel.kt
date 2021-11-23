package com.diegolima.TheMoviesTMDb.ui.toprateddetail

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
class TopRatedViewModel @Inject constructor(
    private val repository: MovieRepositoryInterface
): ViewModel(){
    private val moviesTopRated = MutableLiveData<NetworkResponse<MovieResponse>>()

    val moviesListTopRated: LiveData<NetworkResponse<MovieResponse>>
        get() = moviesTopRated

    var movie: Result? = null

    fun getMoviesTopRated(){
        moviesTopRated.value = NetworkResponse.loading(null)

        viewModelScope.launch {
            val response = repository.getMoviesTopRated()
            moviesTopRated.value = response
        }
    }

}