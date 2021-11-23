package com.diegolima.TheMoviesTMDb.ui.nowplayingdetail

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
class NowPlayingViewModel @Inject constructor(
    private val repository: MovieRepositoryInterface
): ViewModel(){

    private val moviesNowPlaying = MutableLiveData<NetworkResponse<MovieResponse>>()

    val moviesListNowPlaying: LiveData<NetworkResponse<MovieResponse>>
        get() = moviesNowPlaying

    var movie: Result? = null

    fun getMoviesNowPlaying(){
        moviesNowPlaying.value = NetworkResponse.loading(null)

        viewModelScope.launch {
            val response = repository.getMoviesNowPlaying()
            moviesNowPlaying.value = response
        }
    }

}