package com.diegolima.TheMoviesTMDb.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDetails (

    @Json(name = "runtime")
    val page: Int,

    @Json(name = "genres")
    val genres: List<Genre>,

    @Json(name = "id")
    val total_results: Int
)