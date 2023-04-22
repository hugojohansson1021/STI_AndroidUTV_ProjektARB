package com.example.projektarb.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.projektarb.MovieViewModel
import com.example.projektarb.databinding.FragmentDetailsBinding
import com.example.projektarb.utils.Status
import com.example.projektarb.utils.Status.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {




    lateinit var binding:FragmentDetailsBinding

    val args : DetailsFragmentArgs by navArgs()

    val viewModel:MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.getMovieDetails(args.imdbId!!)

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            val status = it.getContentIfNotHandled()?.status
            if (status != null) {
                if (status == Status.LOADING) {
                    binding.detailsProgress.visibility = View.VISIBLE
                } else if (status == Status.ERROR) {
                    binding.detailsProgress.visibility = View.GONE
                } else if (status == Status.SUCCESS) {
                    binding.detailsProgress.visibility = View.GONE
                    binding.movieDetails = it.peekContent().data
                }
            }
        }


    }

}