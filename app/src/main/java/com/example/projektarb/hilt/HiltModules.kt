package com.example.projektarb.hilt

import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.ui.details.MovieDetailsRepository
import com.example.projektarb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)

// using hilt module to generate in fragment and get values in movieinterface

@Module
object HiltModules {

    @Provides
    fun provideRetrofitInterface(): MovieInterface{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build().create(MovieInterface::class.java)//this chain code provides using hilt, life to MovieInterface.kt values
    }




    @Provides
    fun provideRepository(movieInterface: MovieInterface): MovieDetailsRepository{
        return MovieDetailsRepository(movieInterface)
    }


}