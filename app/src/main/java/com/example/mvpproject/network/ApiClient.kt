package com.example.mvpproject.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

     fun client(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    fun client2(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(Base_UR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val Base_UR = "https://jsonplaceholder.typicode.com/"
        lateinit var retrofit: Retrofit
        val api_key = "cd943fa44ff7482d2f9ef70d608dc261"
    }
}