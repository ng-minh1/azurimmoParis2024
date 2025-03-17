package com.example.azurimmomn.api

import com.example.azurimmomn.model.Actualite
import com.example.azurimmomn.model.Athlete
import retrofit2.http.GET


interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/athletes")
    suspend fun getAthletes(): List<Athlete>
}