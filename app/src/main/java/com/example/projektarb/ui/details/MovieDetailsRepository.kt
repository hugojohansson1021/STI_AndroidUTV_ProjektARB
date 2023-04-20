package com.example.projektarb.ui.details

import com.example.projektarb.data.moviedetails.MovieDetails
import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.utils.Result

class MovieDetailsRepository ( private val movieInterface: MovieInterface) {


    suspend fun  getMovieDetails(imdbId:String): Result<MovieDetails>{

    }


}