package com.example.projektarb.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.projektarb.MovieViewModel
import com.example.projektarb.R
import com.example.projektarb.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {


    lateinit var binding: FragmentMovieBinding

    val viewModel: MovieViewModel by viewModels()

    val movieAdapter = MoviePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMovieBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()





        binding.movieSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it) }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.list.observe(viewLifecycleOwner){
            movieAdapter.submitData(lifecycle, it)
        }


    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter= movieAdapter
            layoutManager= GridLayoutManager(requireContext(),2)
        }
    }


}