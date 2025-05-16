package com.example.pokedrez.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedrez.RetrofitInstance
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.model.Pokemon
import com.example.pokedrez.model.User
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.random.Random


class GameViewModel  (private val sessionManager: SessionManager) : ViewModel() {

    lateinit var user: User
    var pokemonList = mutableListOf<Pokemon>()
    private val _equipoJugador = MutableLiveData<List<Pokemon>>(emptyList())
    val equipoJugador: LiveData<List<Pokemon>> get() = _equipoJugador

    private val _equipoEnemigo = MutableLiveData<List<Pokemon>>(emptyList())
    val equipoEnemigo: LiveData<List<Pokemon>> get() = _equipoEnemigo

    private val _tienda = MutableLiveData<List<Pokemon>>(emptyList())
    val tienda: LiveData<List<Pokemon>> get() = _tienda

    init {
        cargarPokemon()
    }

    fun cargarPokemon() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemons()
                pokemonList = response.toMutableList()
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error: ${e.message}")
            }
        }
    }

    fun cargarTienda() {
        // Simulación de carga de tienda desde una API
        tienda.clear()
        for (i in 1..5) {
            tienda.add(
                pokemonList[Random.nextInt(0, 150)]
            )
        }
    }

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