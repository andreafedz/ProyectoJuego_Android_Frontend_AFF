package com.aff.proyectojuegoandreaff.network

import com.aff.proyectojuegoandreaff.model.ResultadoPartida
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/juego/jugar")
    suspend fun jugar(
        @Query("eleccion") eleccion: String
    ): ResultadoPartida
}
