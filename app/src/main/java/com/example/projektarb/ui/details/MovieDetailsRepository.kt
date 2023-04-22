package com.example.projektarb.ui.details

import com.example.projektarb.data.moviedetails.MovieDetails
import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.utils.Constants
import com.example.projektarb.utils.Result
import com.example.projektarb.utils.Status

class MovieDetailsRepository ( private val movieInterface: MovieInterface) {


    suspend fun  getMovieDetails(imdbId:String): Result<MovieDetails>{


        return try {

            val response= movieInterface.getMovieDetails(imdbId,Constants.API_KEY)
            if(response.isSuccessful){


                Result(Status.SUCCESS,response.body(),null)

            }else{
                Result(Status.ERROR,null, null )
            }



        }catch (e:Exception){
            Result(Status.ERROR,null, null )
        }


    }


}