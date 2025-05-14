package com.example.pokedrez.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ViewModel.DashboardViewModel

// Factory para crear el ViewModel con el parámetro SessionManager
class DashboardViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    // El método create se utiliza para crear una instancia del ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            // Crea la instancia de LoginViewModel pasando sessionManager
            return DashboardViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}