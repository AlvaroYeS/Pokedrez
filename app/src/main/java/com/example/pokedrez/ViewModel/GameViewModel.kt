package com.example.pokedrez.ViewModel

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedrez.RetrofitInstance
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.model.Pokemon
import com.example.pokedrez.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.math.log
import kotlin.random.Random


class GameViewModel  (private val sessionManager: SessionManager) : ViewModel() {

    var pokemonList = mutableListOf<Pokemon>()
    private val _equipoJugador = MutableLiveData<List<Pokemon>>(emptyList())
    val equipoJugador: LiveData<List<Pokemon>> get() = _equipoJugador

    private val _equipoEnemigo = MutableLiveData<List<Pokemon>>(emptyList())
    val equipoEnemigo: LiveData<List<Pokemon>> get() = _equipoEnemigo

    private val _tienda = MutableLiveData<List<Pokemon>>(emptyList())
    val tienda: LiveData<List<Pokemon>> get() = _tienda

    private val _eleccion = MutableLiveData<Boolean>()
    val eleccion: LiveData<Boolean> get() = _eleccion

    var enemigo = "Vivo"
    var jugador = "Vivo"

    private val _partida = MutableLiveData<String>()
    val partida: LiveData<String> get() = _partida


    init {
        cargarPokemon()
    }
    fun iniciarJuego() {
        println("iniciando juego")
        viewModelScope.launch {
            game()
        }
    }
    fun cargarPokemon() {
        println("cargando pokemon")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemons()
                pokemonList = response.toMutableList()
                println("prueba carga" + pokemonList.get(0).namePokemon)
                iniciarJuego()
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error al cargar Pokémon", e)
            }
        }
    }

    fun cargarTienda() {
        println("cargando tienda")
        if (pokemonList.isEmpty()) return

        val nuevaTienda = List(5) {
            pokemonList[Random.nextInt(pokemonList.size)]
        }

        _tienda.postValue(nuevaTienda)
    }

//    fun generarEquipoEnemigo() {
//        val nuevoEquipo = List(6) { i ->
//            Pokemon(
//                id = i + 1,
//                nombre = "Enemigo ${i + 1}",
//                tipo1 = "Físico",
//                tipo2 = "Físico",
//                hp = 100,
//                ad = 20,
//                ap = 10,
//                armor = 5,
//                mr = 5,
//                haste = 1.0,
//                mana = 50,
//                tier = 1,
//                evo = 0
//            )
//        }

//        _equipoEnemigo.value = nuevoEquipo // si estás en el hilo principal
//        // o _equipoEnemigo.postValue(nuevoEquipo) // si estás en un hilo secundario
//    }


//    fun agregarAlEquipo(jugador: Pokemon) {
//        val equipoActual = _equipoJugador.value ?: emptyList()
//        if (equipoActual.size < 6) {
//            val nuevoEquipo = equipoActual + jugador
//            _equipoJugador.value = nuevoEquipo // o postValue si estás en un hilo secundario
//        }
//    }

//    fun iniciarCombate(): Boolean {
//        val hpJugador = equipoJugador.sumOf { it.hp }
//        val hpEnemigo = equipoEnemigo.sumOf { it.hp }
//
//        // Simulación de combate: el equipo con mayor HP total gana
//        return if (hpJugador >= hpEnemigo) {
//            user.floorsCleared++
//            true
//        } else {
//            false
//        }
//    }

    fun addPokemon(pokemon: Pokemon) {
        println("añadiendo pokemon")
        val listaActual = _equipoJugador.value ?: emptyList()
        val nuevaLista = listaActual + pokemon
        _equipoJugador.postValue(nuevaLista)
    }
    fun addEnemigo(){
        println("añadiendo equipo enemigo")
        if (!pokemonList.isEmpty()) {
            var pokemon = pokemonList[Random.nextInt(pokemonList.size)]
            val listaActual = _equipoEnemigo.value ?: emptyList()
            val nuevaLista = listaActual + pokemon
            _equipoEnemigo.postValue(nuevaLista)
            println("equipo enemigo: "+equipoEnemigo)
        }
    }
    fun setEleccion(valor: Boolean) {
        println("añadiendo eleccion")
        _eleccion.value = valor
    }

    suspend fun game() {
        println("iniciando juego")
        while (enemigo == "Vivo" && jugador == "Vivo") {
            _eleccion.value = false // Reiniciar para la próxima ronda
            cargarTienda()
            addEnemigo()
            awaitEleccion()
            combate()
            verificarEquiposVacios()
        }
    }
    private suspend fun awaitEleccion() {
        while (_eleccion.value != true) {
            delay(100) // Espera sin bloquear el hilo
        }
    }
    fun combate() {
        println("iniciando combate")

        val equipoJugador = _equipoJugador.value?.map { it.copy() }?.toMutableList() ?: mutableListOf()
        val equipoEnemigo = _equipoEnemigo.value?.map { it.copy() }?.toMutableList() ?: mutableListOf()

        // Turno del jugador: cada Pokémon ataca una vez
        for (atacante in equipoJugador.toList()) {
            val objetivo = equipoEnemigo.randomOrNull()
            if (objetivo != null) {
                objetivo.hp -= atacante.ad
                if (objetivo.hp <= 0) {
                    equipoEnemigo.remove(objetivo)
                }
            }
        }

        // Turno del enemigo: cada Pokémon ataca una vez
        for (atacante in equipoEnemigo.toList()) {
            val objetivo = equipoJugador.randomOrNull()
            if (objetivo != null) {
                objetivo.hp -= atacante.ad
                if (objetivo.hp <= 0) {
                    equipoJugador.remove(objetivo)
                }
            }
        }

        // Actualiza los equipos finales en LiveData
        _equipoJugador.value = equipoJugador
        _equipoEnemigo.value = equipoEnemigo

    }
    fun verificarEquiposVacios(){
        println("verificando equipos")
        val equipoJugador = _equipoJugador.value ?: emptyList()
        val equipoEnemigo = _equipoEnemigo.value ?: emptyList()

        if (equipoEnemigo.isEmpty()){
            enemigo = "Muerto"
            _partida.value = "Victoria"
        }
        if (equipoJugador.isEmpty()){
            jugador = "Muerto"
            _partida.value = "Derrota"
        }
    }
}