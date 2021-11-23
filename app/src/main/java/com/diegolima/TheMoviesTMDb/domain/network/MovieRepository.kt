package com.diegolima.TheMoviesTMDb.domain.network

import com.diegolima.TheMoviesTMDb.core.network.NetworkResponse
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
): MovieRepositoryInterface {

    override suspend fun getMoviesNowPlaying(): NetworkResponse<MovieResponse> {
        return try {
            val response = movieService.getMoviesNowPlaying()

            if (response.isSuccessful){
                response.body()?.let {
                    return@let NetworkResponse.success(it)
                } ?: NetworkResponse.error("Error", null)
            }else{
                NetworkResponse.error("Error", null)
            }
        }catch (e: Exception){
            NetworkResponse.error("${e.message}", null)
        }
    }

    override suspend fun getMoviesTopRated(): NetworkResponse<MovieResponse> {
        return try {
            val response = movieService.getMoviesTopRated()

            if (response.isSuccessful){
                response.body()?.let {
                    return@let NetworkResponse.success(it)
                } ?: NetworkResponse.error("Error", null)
            }else{
                NetworkResponse.error("Error", null)
            }
        }catch (e: Exception){
            NetworkResponse.error("${e.message}", null)
        }
    }

    override suspend fun getMoviesUpcoming(): NetworkResponse<MovieResponse> {
        return try {
            val response = movieService.getMoviesUpcoming()

            if (response.isSuccessful){
                response.body()?.let {
                    return@let NetworkResponse.success(it)
                } ?: NetworkResponse.error("Error", null)
            }else{
                NetworkResponse.error("Error", null)
            }
        }catch (e: Exception){
            NetworkResponse.error("${e.message}", null)
        }
    }

    override suspend fun getMoviesPopular(): NetworkResponse<MovieResponse> {
        return try {
            val response = movieService.getMoviesPopular()

            if (response.isSuccessful){
                response.body()?.let {
                    return@let NetworkResponse.success(it)
                } ?: NetworkResponse.error("Error", null)
            }else{
                NetworkResponse.error("Error", null)
            }
        }catch (e: Exception){
            NetworkResponse.error("${e.message}", null)
        }
    }


}