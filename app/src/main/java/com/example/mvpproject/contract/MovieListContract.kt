package com.example.mvpproject.contract

import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model2.PhotosItem

interface MovieListContract {
    interface Model{
        interface OnFinishListener{
            fun onFinished(movieArrayList: List<ProductionCompany>)
            fun onFailure(throwable: Throwable)

        }
        fun movieList(onFinishListener: OnFinishListener)
    }
    interface View{
        fun showProgress()
        fun hideProgress()
        fun setData(movieList: List<ProductionCompany>)
        fun onFailure(throwable: Throwable)
    }
    interface Presenter{
        fun onDestroy()
         fun getMoreData()
         fun requestDataFromServer()

    }
}