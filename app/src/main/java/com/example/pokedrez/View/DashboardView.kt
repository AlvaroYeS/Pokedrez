package com.example.pokedrez.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.R
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.DashboardViewModel
import com.example.pokedrez.ViewModelFactory.DashboardViewModelFactory
import androidx.compose.ui.graphics.Brush


@Composable
fun DashboardScreen(navController: NavController, sessionManager: SessionManager) {
    val dashboardViewModel: DashboardViewModel =
        viewModel(factory = DashboardViewModelFactory(sessionManager))
    // val records = dashboardViewModel.records.collectAsState(initial = emptyList())

    Box(modifier = Modifier.fillMaxSize()) {

        // Imagen de fondo opcional
        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido a Pokedrez",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("Game") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF00C853), Color(0xFF64DD17))
                            ),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JUGAR",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Historial de pisos alcanzados (simulado)
            /*
            Text(
                text = "Historial de Pisos:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(records.value) { record ->
                    Text(
                        text = "Piso alcanzado: ${record.pisos}",
                        color = Color.White
                    )
                }
            }
            */
        }
    }
}
