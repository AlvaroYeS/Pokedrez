package com.example.pokedrez.ViewModel

import androidx.lifecycle.ViewModel
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.model.Pokemon
import com.example.pokedrez.model.User


class GameViewModel  (private val sessionManager: SessionManager) : ViewModel() {

    lateinit var user: User
    val equipoJugador = mutableListOf<Pokemon>()
    val equipoEnemigo = mutableListOf<Pokemon>()
    val tienda = mutableListOf<Pokemon>()

   /*TODO*/
//    fun iniciarSesion(username: String, password: String): Boolean {
//        // Simulación de autenticación
//        if (username.isNotEmpty() && password.isNotEmpty()) {
//            user = User(x)
//            return true
//        }
//        return false
//    }

    /*TODO*/
    fun cargarTienda() {
        // Simulación de carga de tienda desde una API
        tienda.clear()
        for (i in 1..5) {
            tienda.add(
                Pokemon(
                    id = i,
                    nombre = "$i",
                    tipo1 = "Físico",
                    tipo2 = "Físico",
                    hp = 100,
                    ad = 20,
                    ap = 10,
                    armor = 5,
                    mr = 5,
                    haste = 1.0,
                    mana = 50,
                    tier = 1,
                    evo = false
                )
            )
        }
    }

    /*TODO*/
    fun generarEquipoEnemigo() {
        // Simulación de generación de equipo enemigo
        equipoEnemigo.clear()
        for (i in 1..3) {
            equipoEnemigo.add(
                Pokemon(
                    id = i,
                    nombre = "$i",
                    tipo1 = "Físico",
                    tipo2 = "Físico",
                    hp = 100,
                    ad = 20,
                    ap = 10,
                    armor = 5,
                    mr = 5,
                    haste = 1.0,
                    mana = 50,
                    tier = 1,
                    evo = false
                )
            )
        }
    }

    fun agregarAlEquipo(jugador: Pokemon) {
        if (equipoJugador.size < 6) {
            equipoJugador.add(jugador)
        }
    }

    fun iniciarCombate(): Boolean {
        val hpJugador = equipoJugador.sumOf { it.hp }
        val hpEnemigo = equipoEnemigo.sumOf { it.hp }

        // Simulación de combate: el equipo con mayor HP total gana
        return if (hpJugador >= hpEnemigo) {
            user.floorsCleared++
            true
        } else {
            false
        }
    }

    fun reiniciarJuego() {
        equipoJugador.clear()
        equipoEnemigo.clear()
        tienda.clear()
    }
}