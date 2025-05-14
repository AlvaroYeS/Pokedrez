package com.example.pokedrez.View


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.GameViewModel
import com.example.pokedrez.ViewModelFactory.GameScreenModelFactory

@Composable
fun GameScreen(navController: NavController, sessionManager: SessionManager) {
    val GameScreenViewModel: GameViewModel = viewModel(factory = GameScreenModelFactory(sessionManager))

    var combateIniciado by remember { mutableStateOf(false) }
    var resultadoCombate by remember { mutableStateOf("") }
/*
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tienda de Objetos", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(viewModel.tienda.size) { index ->
                val item = viewModel.tienda[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.nombre)
                    Button(onClick = { viewModel.agregarAlEquipo(item) }) {
                        Text("Agregar")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Equipo del Jugador", style = MaterialTheme.typography.headlineSmall)
        viewModel.equipoJugador.forEach {
            Text(it.nombre)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Equipo Enemigo", style = MaterialTheme.typography.headlineSmall)
        viewModel.equipoEnemigo.forEach {
            Text(it.nombre)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                combateIniciado = true
                val resultado = viewModel.iniciarCombate()
                resultadoCombate = if (resultado) "¡Victoria!" else "Derrota"
                onGameEnd(resultado)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Combate")
        }
        if (combateIniciado) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(resultadoCombate, style = MaterialTheme.typography.headlineSmall)
        }
    }*/
}