package com.diegolima.TheMoviesTMDb.ui.upcomingdetail

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
class UpcomingViewModel @Inject constructor(
    private val repository: MovieRepositoryInterface
): ViewModel(){
    private val moviesUpcoming = MutableLiveData<NetworkResponse<MovieResponse>>()

    val moviesListUpcoming: LiveData<NetworkResponse<MovieResponse>>
        get() = moviesUpcoming

    var movie: Result? = null

    fun getMoviesUpcoming(){
        moviesUpcoming.value = NetworkResponse.loading(null)

        viewModelScope.launch {
            val response = repository.getMoviesUpcoming()
            moviesUpcoming.value = response
        }
    }

}