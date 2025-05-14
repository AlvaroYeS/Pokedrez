package com.example.pokedrez.model

data class Pokemon(
    val id: Int,
    val nombre: String,
    val tipo1: String,
    val tipo2: String,
    val hp: Int,
    val ad: Int,
    val ap: Int,
    val armor: Int,
    val mr: Int,
    val haste: Double,
    val mana: Int,
    val tier: Int,
    val evo: Boolean
)
