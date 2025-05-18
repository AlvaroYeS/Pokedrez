package com.example.pokedrez.model

data class Pokemon(
    val idPokemon: Int,
    val namePokemon: String,
    val type1: String,
    val type2: String,
    var hp: Int,
    val ad: Int,
    val ap: Int,
    val armor: Int,
    val mr: Int,
    val haste: Double,
    val mana: Int,
    val imagenTienda: String,
    val imagenFrente: String,
    val imagenTrasera: String,
    val tier: Int,
    val evo: Int
)
