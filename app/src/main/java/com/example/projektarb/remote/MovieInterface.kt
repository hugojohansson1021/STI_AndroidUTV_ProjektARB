package com.example.projektarb.remote

import com.example.projektarb.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieInterface {



    // get request to server, provide movie data
    @GET("/")
    suspend fun getAllMovies(

        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String,
    ): Response<MovieResponse>


}