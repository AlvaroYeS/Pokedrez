package com.example.pokedrez

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pokedrez.NavManager.NavManager
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.ui.theme.PokedrezTheme

class MainActivity : ComponentActivity() {
    private lateinit var sessionManager: SessionManager
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        setContent {
            PokedrezTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(sessionManager)
                }
            }
        }

    }
}
