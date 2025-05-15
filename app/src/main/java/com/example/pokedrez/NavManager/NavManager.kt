package com.example.pokedrez.NavManager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.View.GameScreen
import com.example.pokedrez.View.DashboardScreen
import com.example.pokedrez.View.LoginScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavManager(sessionManager: SessionManager) {

    // rememberNavController() crea un objeto NavController y lo recuerda a través del ciclo de vida de la composición.
    val navController = rememberNavController()

    // NavHost es un contenedor que mantiene y gestiona el flujo de navegación de la aplicación.
    // startDestination se establece dependiendo de si el usuario está logueado o no.
    // Si loginSuccess es true, la pantalla inicial será "Home", de lo contrario, será "Login".
    //NavHost(navController = navController, startDestination = if (loginSuccess) "Home" else "Login") {
    NavHost(navController = navController, startDestination = "GameScreen") {

        // Pantalla de Home: Si el usuario está logueado, esta pantalla se muestra
        composable("DashBoard") {
            DashboardScreen(navController, sessionManager)
        }

        // Pantalla de Login: Si el usuario no está logueado, se muestra esta pantalla
        composable("Login") {
            LoginScreen(navController, sessionManager)
        }

        composable("Game") {
            GameScreen (navController, sessionManager)
        }

    }
}