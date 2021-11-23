package com.diegolima.TheMoviesTMDb.domain.network

import com.diegolima.TheMoviesTMDb.core.network.NetworkResponse
import com.diegolima.TheMoviesTMDb.domain.model.MovieResponse
import com.diegolima.TheMoviesTMDb.domain.model.Result

class MockMovieRepositories : MovieRepositoryInterface {

    private var movieNowPlaying = getResultNowPlaying()
    private var movieTopRated = getResultTopRated()
    private var movieUpcoming = getResultUpcoming()
    private var moviePopular = getResultPopular()


    private var moviesNowPlaying = mutableListOf(movieNowPlaying)
    private var moviesTopRated = mutableListOf(movieTopRated)
    private var moviesUpcoming = mutableListOf(movieUpcoming)
    private var moviesPopular = mutableListOf(moviePopular)

    override suspend fun getMoviesNowPlaying(): NetworkResponse<MovieResponse> {
        return NetworkResponse.success(MovieResponse(1, moviesNowPlaying, 1, 1))
    }

    override suspend fun getMoviesTopRated(): NetworkResponse<MovieResponse> {
        return NetworkResponse.success(MovieResponse(9, moviesTopRated, 1, 1))
    }

    override suspend fun getMoviesUpcoming(): NetworkResponse<MovieResponse> {
        return NetworkResponse.success(MovieResponse(1, moviesUpcoming, 1, 1))
    }

    override suspend fun getMoviesPopular(): NetworkResponse<MovieResponse> {
        return NetworkResponse.success(MovieResponse(2, moviesPopular, 1, 1))
    }

    private fun getResultNowPlaying() = Result(
        "/uzERcfV2rSHNhW5eViQiO9hNiA7.jpg",
        false,
        "Nesta viagem mítica e emocional, “Dune - Duna” conta a história de Paul Atreides, um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.",
        "2021-09-15",
        listOf(878, 12),
        438631,
        "Dune",
        "en",
        "Duna",
        "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
        2478.539,
        1000,
        false,
        8.0
    )

    private fun getResultTopRated() = Result(
        "/ERcfV2rSHNhW5eViQiO9hNiA.jpg",
        true,
        "Viagem mítica e emocional, “Dune - Duna” conta a história de Paul Atreides, um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.",
        "2021-09-10",
        listOf(878, 12),
        438631,
        "Filme",
        "en",
        "Duna",
        "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
        2478.539,
        1000,
        false,
        10.0
    )

    private fun getResultUpcoming() = Result(
        "/zERcfV2rSHNhW5eViQiO9hNiA7.jpg",
        true,
        "“Dune - Duna” conta a história de Paul Atreides, um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.",
        "2021-09-10",
        listOf(878, 12),
        438631,
        "Filme",
        "en",
        "Filme",
        "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
        2478.539,
        1000,
        false,
        10.0
    )

    private fun getResultPopular() = Result(
        "/zERcfV2rSHNhW5eViQiO9hNiA7.jpg",
        true,
        "Um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.",
        "2021-09-01",
        listOf(878, 12),
        438631,
        "Venon",
        "en",
        "Venon",
        "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
        2478.539,
        1000,
        false,
        10.0
    )
}