package com.example.azurimmomn.api

import com.example.azurimmomn.model.Actualite
import com.example.azurimmomn.model.Athlete
import com.example.azurimmomn.model.Epreuve
import retrofit2.http.GET


interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/athletes")
    suspend fun getAthletes(): List<Athlete>

    @GET("/epreuves")
    suspend fun getEpreuves(): List<Epreuve>
}