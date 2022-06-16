package com.example.mvpproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpproject.contract.MovieListContract
import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model2.PhotosItem
import com.example.mvpproject.presenter.MoviePresenter
import com.example.mvpproject.service.MovieListModel
import com.example.mvpproject.view.MovieListAdapter

class MainActivity : AppCompatActivity(), MovieListContract.View {
    private lateinit var moviePresenter: MoviePresenter
    private lateinit var recyclerView: RecyclerView
    private var movieLists = ArrayList<ProductionCompany>()
    private lateinit var progressBar: ProgressBar
    private lateinit var adapters: MovieListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var movieListModel: MovieListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)
        progressBar = findViewById(R.id.progress)
        movieLists = ArrayList()
        movieListModel = MovieListModel()
        linearLayoutManager = LinearLayoutManager(this)

        adapters = movieLists.let { MovieListAdapter(it,this) }

        recyclerView.apply {
            layoutManager = linearLayoutManager
            hasFixedSize()
        }
        moviePresenter = MoviePresenter(this,movieListModel)
        moviePresenter.requestDataFromServer()
        setData(movieLists)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setData(movieList: List<ProductionCompany>) {
        if (movieList != null) {
            movieLists.addAll(movieList)
            adapters = MovieListAdapter(movieList,this)
            recyclerView.adapter = adapters
            adapters.setAdapterData(movieList)
        }
    }

    override fun onFailure(throwable: Throwable) {
        Log.e("Error","Error "+ throwable.message)
        Toast.makeText(this,throwable.message.toString(),Toast.LENGTH_SHORT).show()
    }
}