package com.example.projektarb.remote

import com.example.projektarb.data.MovieResponse
import com.example.projektarb.data.moviedetails.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieInterface {



    // get request to remote server, to provide movie data
    @GET("/")
    suspend fun getAllMovies(

        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String,
    ): Response<MovieResponse>



    @GET("/")
    suspend fun getMovieDetails(

        @Query("i") imdbId: String,
        @Query("apiKey")apiKey: String,
    ): Response<MovieDetails>
}