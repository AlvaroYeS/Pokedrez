package com.example.pokedrez.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedrez.RetrofitInstance
import com.example.pokedrez.SessionManager.SessionManager
import com.example.pokedrez.model.LoginRequest
import com.example.pokedrez.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.security.MessageDigest

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {
    // `mutableStateOf` es usado para crear una propiedad observable, lo que permite que la UI se actualice automáticamente
    // cuando el valor de estas propiedades cambia. En este caso, son las propiedades de los campos de texto y el estado de login.

    // Para manejar los estados de los campos de texto de nombre de usuario y contraseña
    var nameUser = mutableStateOf("")  // Almacenará el valor del nombre de usuario ingresado.
    var passUser = mutableStateOf("")  // Almacenará el valor de la contraseña ingresada.

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(alias:String, contraseña: String) {
        sessionManager.clearSession()

        val hashedPassword = hashPassword(contraseña)  // Hash de la contraseña

        println("📡 Enviando login con usuario: ${alias}, contraseña: $hashedPassword")
        val loginRequest = LoginRequest(alias, hashedPassword)

        RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginSuccess.value = true
                    println("✅ Login Correcto")

                    val user = response.body()?.user

                    if (user != null ) {
                        println("Usuario logueado: ${user.id} - ${user.alias}")

                        sessionManager.saveUserId(user.id) }
                } else {
                    _errorMessage.value = "❌ Credenciales incorrectas"
                    _loginSuccess.value = false
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _errorMessage.value = "❌ Error de conexión: ${t.message}"
                _loginSuccess.value = false
            }
        })
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }  // Convierte a hexadecimal
    }

}