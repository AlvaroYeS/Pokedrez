package com.example.pokedrez.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.LoginViewModel
import com.example.pokedrez.ViewModelFactory.LoginViewModelFactory

@Composable
fun LoginScreen(navController: NavController, sessionManager: SessionManager) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(sessionManager))
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Llamamos al método login del loginViewModel para intentar realizar el inicio de sesión.

            loginViewModel.login(username, password)

        }, modifier = Modifier
            .fillMaxWidth()  // El botón ocupa el ancho completo disponible.
            .padding(start = 30.dp, end = 30.dp),  // Padding a los lados del botón.
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green, // Cambia el color de fondo del botón
                contentColor = Color.White // Cambia el color del texto del botón
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            // El texto dentro del botón es "Entrar"
            Text(text = "Entrar")
        }
    }
}