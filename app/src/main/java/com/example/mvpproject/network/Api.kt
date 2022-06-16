package com.example.mvpproject.network

import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model2.Photos
import com.example.mvpproject.model2.PhotosItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/550")
    fun popularMovies(@Query("api_key") api: String): Call<Movie>

    @GET("photos")
    fun photos():Call<List<PhotosItem>>
}