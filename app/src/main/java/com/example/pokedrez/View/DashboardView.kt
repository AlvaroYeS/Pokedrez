package com.example.pokedrez.View

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.DashboardViewModel
import com.example.pokedrez.ViewModelFactory.DashboardViewModelFactory

@Composable
fun DashboardScreen(navController: NavController, sessionManager: SessionManager) {
    val DashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModelFactory(sessionManager))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){}
}