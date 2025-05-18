package com.example.pokedrez.View

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import okhttp3.internal.immutableListOf
import java.io.File


@Composable
fun GameScreen(navController: NavController, sessionManager: SessionManager) {
    val gameScreenViewModel: GameViewModel = viewModel(factory = GameScreenModelFactory(sessionManager))
    val equipoJugador by gameScreenViewModel.equipoJugador.observeAsState(emptyList())
    val equipoEnemigo by gameScreenViewModel.equipoEnemigo.observeAsState(emptyList())
    val tienda by gameScreenViewModel.tienda.observeAsState(emptyList())
    val context = LocalContext.current
    val partida by gameScreenViewModel.partida.observeAsState("")


    LaunchedEffect(partida) {
        if(partida != ""){
            sessionManager.saveResultado(partida)
            navController.navigate("Resultado")
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.bgarena),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        // Contenido encima de la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 350.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(equipoEnemigo) { pokemon ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val resourceName = pokemon.imagenFrente.removeSuffix(".png")
                        val imageId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

                        if (imageId != 0) {
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = "imagenFrente",
                                modifier = Modifier.size(80.dp)
                            )
                        } else {
                            Text("Imagen no encontrada")
                        }
                        Text(text = "HP: ${pokemon.hp}",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold)
                    }
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
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val resourceName = pokemon.imagenTrasera.removeSuffix(".png")
                        val imageId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

                        if (imageId != 0) {
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = "imagenTrasera",
                                modifier = Modifier.size(80.dp)
                            )
                        } else {
                            Text("Imagen no encontrada")
                        }
                        Text(text = "HP: ${pokemon.hp}",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Row 3 - Tienda

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 85.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(tienda) { pokemon ->
                    val resourceName = pokemon.imagenTienda.removeSuffix(".png")
                    val imageId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

                    Button(
                        onClick = {
                            gameScreenViewModel.addPokemon(pokemon)
                            gameScreenViewModel.setEleccion(true)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .size(80.dp)
                    ) {
                        if (imageId != 0) {
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = "Imagen de ${pokemon.namePokemon}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Text(
                                text = "Imagen no encontrada",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

