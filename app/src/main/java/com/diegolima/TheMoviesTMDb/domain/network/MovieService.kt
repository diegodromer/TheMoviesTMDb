package com.diegolima.TheMoviesTMDb.domain.network

import com.diegolima.TheMoviesTMDb.BuildConfig
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponse
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponseDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMoviesDetailId(
        @Query("append_to_response")
        appendtoresponse: String = "",

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponseDetails>

    @GET("movie/{movie_id}/similar")
    suspend fun getMoviesSimilarId(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMoviesReviewsId(
        @Query("page")
        page: Int = BuildConfig.PAGE_START,

        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,

        @Query("language")
        language: String = BuildConfig.LANGUAGE
    ): Response<MovieResponse>
}