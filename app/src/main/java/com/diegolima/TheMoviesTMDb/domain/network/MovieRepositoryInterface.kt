package com.diegolima.TheMoviesTMDb.domain.network

import com.diegolima.TheMoviesTMDb.core.network.NetworkResponse
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponse

interface MovieRepositoryInterface {
    suspend fun getMoviesNowPlaying(): NetworkResponse<MovieResponse>
    suspend fun getMoviesTopRated(): NetworkResponse<MovieResponse>
    suspend fun getMoviesUpcoming(): NetworkResponse<MovieResponse>
    suspend fun getMoviesPopular(): NetworkResponse<MovieResponse>
}