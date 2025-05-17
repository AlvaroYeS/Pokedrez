package com.example.pokedrez.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.R
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.GameViewModel
import com.example.pokedrez.ViewModelFactory.GameScreenModelFactory
import com.example.pokedrez.model.Pokemon
import androidx.compose.runtime.setValue // ← Este puede ser necesario si usas `var` con `by`


@Composable
fun GameScreen(navController: NavController, sessionManager: SessionManager) {
    val gameScreenViewModel: GameViewModel = viewModel(factory = GameScreenModelFactory(sessionManager))
    val equipoJugador by gameScreenViewModel.equipoJugador.observeAsState(emptyList())
    val equipoEnemigo by gameScreenViewModel.equipoEnemigo.observeAsState(emptyList())
    val tienda by gameScreenViewModel.tienda.observeAsState(emptyList())

        Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        // Contenido encima de la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                items(equipoEnemigo) { pokemon ->
                    Text(text = pokemon.namePokemon)
                    Text(text = pokemon.hp.toString())
                }
            }
            // Row 2 - Jugador
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                items(equipoJugador) { pokemon ->
                    Text(text = pokemon.namePokemon)
                    Text(text = pokemon.hp.toString())
                }
            }

            // Row 3 - Tienda

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                items(tienda) { pokemon ->
                    Button(onClick = {gameScreenViewModel.addPokemon(pokemon)
                        gameScreenViewModel.setEleccion(true)}
                    ) {
                        Text(text = pokemon.namePokemon)
                    }
                }
            }
        }
    }
}

