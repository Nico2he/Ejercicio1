package com.example.ejercicio1.model

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET ("discover/movie?language=es-ES&sort_by=popularity.desc")
    suspend fun popularMovies(@Query("api_key")apiKey: String): RemoteResult
}