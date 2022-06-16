package com.example.mvpproject.service

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.mvpproject.MainActivity
import com.example.mvpproject.contract.MovieListContract
import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model2.Photos
import com.example.mvpproject.model2.PhotosItem
import com.example.mvpproject.network.Api
import com.example.mvpproject.network.ApiClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListModel: MovieListContract.Model {
    private val TAG: String = " MovieListModel"
    private lateinit var throwable: Throwable
    override fun movieList(
        onFinishListener: MovieListContract.Model.OnFinishListener,
    ) {
        val apiClient = ApiClient()
        val api: Api = apiClient.client().create(Api::class.java)
        //val api: Api = apiClient.client2().create(Api::class.java)
        val call: Call<Movie> = api.popularMovies(ApiClient.api_key)
        call.enqueue(object: Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful) {
                    val movie: List<ProductionCompany>? = response.body()?.productionCompanies
                    Log.v("MOV","Movies are "+movie)
                    onFinishListener.onFinished(movie!!)
                }else{
                    Log.e("RRR",response.message())
                    onFinishListener.onFailure(throwable)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("ERR",t.message.toString())
            }

        })

    }

    companion object{
        val pageNumber: Int = 3
    }

}