package com.example.pokedrez

import android.annotation.SuppressLint
import android.content.Context
import com.example.pokedrez.Api.ApiService
import com.example.pokedrez.SessionManager.SessionManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object RetrofitInstance {
    private lateinit var context: Context
    private lateinit var sessionManager: SessionManager
    private const val BASE_URL = "https://ap-production-bf33.up.railway.app"
    fun init(context: Context) {
        this.context = context.applicationContext
        sessionManager = SessionManager(context)
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}