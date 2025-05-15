package com.example.pokedrez.View



import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.GameViewModel
import com.example.pokedrez.ViewModelFactory.GameScreenModelFactory

@Composable
fun GameScreen(navController: NavController, sessionManager: SessionManager) {
    val GameScreenViewModel: GameViewModel = viewModel(factory = GameScreenModelFactory(sessionManager))


}