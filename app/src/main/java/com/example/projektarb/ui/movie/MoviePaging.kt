package com.example.projektarb.ui.movie

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.projektarb.data.Movie
import com.example.projektarb.remote.MovieInterface
import com.example.projektarb.utils.Constants

class MoviePaging(val s: String, val movieInterface: MovieInterface) : PagingSource<Int, Movie>() {


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//getRefreshKey using state

// if not null, create new variable (ancorepage) and by using state we get closest page to this
// position"it".
        return state.anchorPosition?.let{
            val anchorPage = state.closestPageToPosition(it)
// if ancorepage have pevKey then add 1 to it; otherwise ancorepage nextkey minus 1
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//page variable= pagenumber.
//if params.key = null return 1


        val page= params.key ?: 1

        return try {

            val data= movieInterface.getAllMovies(s,page,Constants.API_KEY)
            Log.d("TAG", "load: ${data.body()}")

            LoadResult.Page(
                data = data.body()?.Search!!,//returns list of movies
                prevKey = if(page== 1) null else page - 1,
                nextKey = if(data.body()?.Search?.isEmpty()!!)null else page + 1



            )



        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }



    }
}