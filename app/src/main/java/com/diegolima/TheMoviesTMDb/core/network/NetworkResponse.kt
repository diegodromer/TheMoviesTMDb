package com.diegolima.TheMoviesTMDb.core.network

data class NetworkResponse <out T> (val status: Status, val data: T?, val message: String?){
    companion object {

        fun <T> success(data: T?): NetworkResponse <T> = NetworkResponse(Status.SUCCESS, data, null)

        fun <T> error(msg: String, data: T?): NetworkResponse <T> = NetworkResponse(Status.ERROR, data, msg)

        fun <T> loading(data: T?): NetworkResponse<T> = NetworkResponse(Status.LOADING, data, null)
    }
}

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}