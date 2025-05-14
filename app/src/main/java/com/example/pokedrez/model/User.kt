package com.example.pokedrez.model

data class User(
    val id: Int,
    val alias: String,
    val contraseña: String,
    val correo: String,
    val avatar: String,
    val isAdmin: Boolean,
    var floorsCleared: Int = 0,
)
