package com.example.pokedrez.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.GameViewModel

// Factory para crear el ViewModel con el parámetro SessionManager
class GameScreenModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    // El método create se utiliza para crear una instancia del ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            // Crea la instancia de LoginViewModel pasando sessionManager
            return GameViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}