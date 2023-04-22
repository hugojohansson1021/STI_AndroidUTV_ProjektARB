package com.example.projektarb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.projektarb.data.moviedetails.MovieDetails
import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.ui.details.MovieDetailsRepository
import com.example.projektarb.ui.movie.MoviePaging
import com.example.projektarb.utils.Events
import com.example.projektarb.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.projektarb.utils.Result

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface, private val repository: MovieDetailsRepository) : ViewModel() {

   private val query= MutableLiveData<String>()


    //using query to make the search dynamic
    //and using switchmap to switch values betwen the live data
    val list= query.switchMap {query ->
        Pager(PagingConfig(pageSize = 10)) {//API will by defoult returl 10 list items
        MoviePaging(query,movieInterface)
    }.liveData.cachedIn(viewModelScope)}

//Function to set query

    fun setQuery(s: String){
        query.postValue(s)
    }

//Set up for movie details




    private val _movieDetails= MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails:LiveData<Events<Result<MovieDetails>>> = _movieDetails


// Func for get movie details W.Scope

    fun getMovieDetails(imdbId:String)= viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))


    }





}