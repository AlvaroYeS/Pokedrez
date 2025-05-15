package com.example.pokedrez.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.DashboardViewModel
import com.example.pokedrez.ViewModelFactory.DashboardViewModelFactory

@Composable
fun DashboardScreen(navController: NavController, sessionManager: SessionManager) {
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModelFactory(sessionManager))
//val records = dashboardViewModel.records.collectAsState(initial = emptyList()) // Usa StateFlow o LiveData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("GameScreen") }) {
            Text(text = "Jugar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
      //      items(records.value) { record ->
        //        Text(text = "Pisos: ${record.pisos}") // Ajusta el acceso a propiedades según tu modelo
            }
        }
    }

