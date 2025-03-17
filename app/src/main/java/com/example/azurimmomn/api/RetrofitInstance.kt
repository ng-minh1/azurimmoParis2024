package com.example.azurimmomn.api

import com.google.gson.JsonDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.format.DateTimeFormatter
import java.time.LocalDate


object RetrofitInstance {

    val localDateTypeAdapter: JsonDeserializer<LocalDate> = JsonDeserializer { json, _, _ ->
        LocalDate.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter)
        .create()

    private const val BASE_URL = "http://10.0.2.2:9005"
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}