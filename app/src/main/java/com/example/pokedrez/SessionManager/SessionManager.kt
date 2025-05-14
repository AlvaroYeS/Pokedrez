package com.example.pokedrez.SessionManager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val USER_ID_KEY = "USER_ID" // Definir la clave como constante

    fun saveUserId(userId: Int) {
        prefs.edit().putInt(USER_ID_KEY, userId).apply()
        Log.d("SessionManager", "User ID saved: $userId")
        Log.d("SessionManager", "Prefs content: ${prefs.all}")
    }

    fun getUserId(): Int {

        val userId = prefs.getInt(USER_ID_KEY, -1)
        Log.d("SessionManager", "User ID retrieved: $userId")
        Log.d("SessionManager", "Prefs content: ${prefs.all}") // Añadir este log
        return userId

        //return prefs.getInt(USER_ID_KEY, -1) // -1 significa que no hay usuario guardado

    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}