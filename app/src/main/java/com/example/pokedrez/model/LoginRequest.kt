package com.example.pokedrez.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("alias") val alias: String,
    @SerializedName("contraseña") val contraseña: String
)