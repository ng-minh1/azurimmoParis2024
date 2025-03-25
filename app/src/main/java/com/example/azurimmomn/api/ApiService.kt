package com.example.azurimmomn.api

import com.example.azurimmomn.model.Actualite
import com.example.azurimmomn.model.Athlete
import com.example.azurimmomn.model.Epreuve
import com.example.azurimmomn.model.Pays
import com.example.azurimmomn.model.Sport
import com.example.azurimmomn.model.Joueur



import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/actualites")
    suspend fun getActualites(): List<Actualite>

    @GET("/athletes")
    suspend fun getAthletes(): List<Athlete>

    @GET("/epreuves")
    suspend fun getEpreuves(): List<Epreuve>

    @GET("/pays")
    suspend fun getPays(): List<Pays>

    @GET("/sports")
    suspend fun getSports(): List<Sport>

    @GET("/joueurs")
    suspend fun getJoueurs(): List<Joueur>

    @GET("/athletes/sport/{sportId}")
    suspend fun getAthletesBySportId(@Path("sportId") sportId: Int): List<Athlete>

    @GET("/sport/{id}")
    suspend fun getSport(@Path("id") id: Int): retrofit2.Response<Sport>

}