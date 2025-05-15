package com.example.pokedrez.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
    val loginSuccess by loginViewModel.loginSuccess.observeAsState(false)


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
            // Llamamos al mé login del loginViewModel para intentar realizar el inicio de sesión.

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
        // Si el login es exitoso, navegamos a la vista de inicio (HomeView).
        // Se observa el estado loginSuccess y si es verdadero, se realiza la navegación.
        if (loginSuccess) {
            // LaunchedEffect permite ejecutar un efecto secundario que se ejecuta una sola vez
            // cuando se produce el cambio en el estado observado.
            LaunchedEffect(Unit) {
                // Navegamos a la ruta "Home" después de un inicio de sesión exitoso.
                navController.navigate("DashBoard")
            }
        }
    }

}
