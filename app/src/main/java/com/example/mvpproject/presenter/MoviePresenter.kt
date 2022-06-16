package com.example.mvpproject.presenter

import com.example.mvpproject.contract.MovieListContract
import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model2.PhotosItem
import com.example.mvpproject.service.MovieListModel

class MoviePresenter(
    var movieListContractView: MovieListContract.View?,
var movieListModel: MovieListModel) :
    MovieListContract.Presenter, MovieListContract.Model.OnFinishListener {

    override fun onDestroy() {
        this.movieListContractView = null
    }

    override fun getMoreData() {
        if(movieListContractView != null){
            movieListContractView?.showProgress()
        }
        movieListModel.movieList(this)
    }

    override fun requestDataFromServer() {
        if(movieListContractView != null){
            movieListContractView?.showProgress()
        }
        movieListModel.movieList(this)
    }

    override fun onFinished(movieArrayList: List<ProductionCompany>) {
        movieListContractView?.setData(movieArrayList)
        if(movieListContractView != null){
            movieListContractView?.hideProgress()
        }
    }

    override fun onFailure(throwable: Throwable) {
        movieListContractView?.onFailure(throwable)
        if(movieListContractView != null){
            movieListContractView?.hideProgress()
        }
    }
}