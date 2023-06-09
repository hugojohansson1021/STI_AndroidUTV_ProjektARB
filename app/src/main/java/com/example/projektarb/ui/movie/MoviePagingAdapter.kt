package com.example.projektarb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projektarb.BR
import com.example.projektarb.data.Movie
import com.example.projektarb.databinding.ViewHolderMovieBinding


class MoviePagingAdapter : PagingDataAdapter<Movie,MoviePagingAdapter.MyViewHolder>(DIFF_UTIL) {



    var onClick : ((String)->Unit)?=null




//make a diffrance betwende the new updt list item and the prev
    companion object{
        val DIFF_UTIL= object : DiffUtil.ItemCallback<Movie>(){

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID==newItem.imdbID
                //making sure the list does not contain the same movie (imdbId)
            }


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem ==newItem
            }
        }
    }




    fun onMovieClick(listener:(String)->Unit){
        onClick= listener
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderMovieBinding): RecyclerView.ViewHolder(viewDataBinding.root)


//setting variables using BR to access view_holder_movie.xml variables
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data= getItem(position)
        holder.viewDataBinding.setVariable(BR.movie,data)

        holder.viewDataBinding.root.setOnClickListener{
            onClick?.let {
                it(data?.imdbID!!)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

}