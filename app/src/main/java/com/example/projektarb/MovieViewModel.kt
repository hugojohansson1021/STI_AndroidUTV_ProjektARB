package com.example.projektarb

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface) : ViewModel() {

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




}