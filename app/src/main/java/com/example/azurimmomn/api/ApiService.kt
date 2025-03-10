package com.example.azurimmomn.api

import com.example.azurimmomn.model.Actualite
import retrofit2.http.GET


interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>
}