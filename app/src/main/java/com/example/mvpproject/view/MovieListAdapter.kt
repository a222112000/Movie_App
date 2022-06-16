package com.example.mvpproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.example.mvpproject.R
import com.example.mvpproject.model.Movie
import com.example.mvpproject.model.ProductionCompany
import com.example.mvpproject.model.ResponseMovie
import com.example.mvpproject.model2.PhotosItem
import com.squareup.picasso.Picasso

class MovieListAdapter(private var movieList: List<ProductionCompany>, private val context: Context): RecyclerView.Adapter<MovieListAdapter.MyviewHolder>() {

    class MyviewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgView: ImageView = itemView.findViewById(R.id.imgView)
        var tvTitle: TextView = itemView.findViewById(R.id.tvmovietitle)
        var tvRelease: TextView = itemView.findViewById(R.id.tvrelease)
        var tvOverview: TextView = itemView.findViewById(R.id.tvoverview)

    }
    fun setAdapterData(items:List<ProductionCompany>){
         movieList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_movie_list,parent,false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
       holder.tvTitle.text = movieList[position].name
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original/"+movieList[position].logoPath)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.imgView);
        holder.tvOverview.text = movieList[position].originCountry
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}