package com.example.pokedrez.Api

import com.example.pokedrez.model.LoginRequest
import com.example.pokedrez.model.LoginResponse
import com.example.pokedrez.model.Pokemon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("pokemons")
    suspend fun getPokemons(): List<Pokemon>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}