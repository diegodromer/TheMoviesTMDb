package com.diegolima.TheMoviesTMDb.ui.populardetail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.diegolima.TheMoviesTMDb.R
import com.diegolima.TheMoviesTMDb.core.network.Status
import com.diegolima.TheMoviesTMDb.databinding.FragmentPopularDetailBinding
import com.diegolima.TheMoviesTMDb.domain.model.Result
import dominando.android.mobile2you.util.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

class PopularDetailFragment @Inject constructor(
    val glide: RequestManager,
    private val popularRecyclerAdapter: PopularRecyclerAdapter
): Fragment(R.layout.fragment_popular_detail) {

    lateinit var viewModel: PopularViewModel
    private var _binding: FragmentPopularDetailBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PopularViewModel::class.java)
        val binding = FragmentPopularDetailBinding.bind(view)
        _binding = binding

        val movie = viewModel.movie

        viewModel.getMoviesPopular()

        subscribeToObservers()

        binding.recyclerViewSimilar.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSimilar.adapter = popularRecyclerAdapter

        popularRecyclerAdapter.setOnMovieItemClickListener { movieSimitarClick ->
            viewModel.movie = movieSimitarClick
            findNavController().navigate(R.id.action_popularDetailFragment_self)
        }

        attachMovies(movie, binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun attachMovies(movie: Result?, binding: FragmentPopularDetailBinding) {
        movie?.let {
            binding.ivBack.setOnClickListener {
                findNavController().navigate(R.id.action_popularDetailFragment_to_movieListFragment)
            }

            val movieBackdrop: Uri = Uri.parse(Constants.BASE_IMAGE + it.backdrop_path)
            glide.load(movieBackdrop)
                .into(binding.ivBackdropPath)

            val moviePoster: Uri = Uri.parse(Constants.BASE_IMAGE + it.poster_path)
            glide.load(moviePoster)
                .into(binding.ivPosterMovie)

            binding.tvTitle.text = it.title
            binding.tvVote.text = it.vote_average.toString()

            if (!it.release_date.isNullOrBlank()) {
                val date = LocalDate.parse(it.release_date)
                val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                val dateFormated = date.format(formatter).toString()
                binding.tvData.text = dateFormated
            }

            binding.tvSinopse.text = resources.getText(R.string.strSinopse)
            if (!it.overview.isNullOrBlank()) {
                binding.tvOverview.text = it.overview
            } else {
                binding.tvOverview.text = "-"
            }

            binding.tvComments.text = resources.getText(R.string.strCommets)
            binding.tvSimilares.text = resources.getText(R.string.strSimilares)
        }
    }

    private fun subscribeToObservers() {
        viewModel.moviesListPopular.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val movies = it.data?.results
                    popularRecyclerAdapter.movies = movies!!
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}