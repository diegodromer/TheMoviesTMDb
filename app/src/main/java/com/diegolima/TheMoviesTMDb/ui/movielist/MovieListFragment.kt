package com.diegolima.TheMoviesTMDb.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegolima.TheMoviesTMDb.R
import com.diegolima.TheMoviesTMDb.core.network.Status
import com.diegolima.TheMoviesTMDb.databinding.FragmentMovieListBinding
import com.diegolima.TheMoviesTMDb.ui.nowplayingdetail.NowPlayingViewModel
import com.diegolima.TheMoviesTMDb.ui.populardetail.PopularViewModel
import com.diegolima.TheMoviesTMDb.ui.toprateddetail.TopRatedViewModel
import com.diegolima.TheMoviesTMDb.ui.upcomingdetail.UpcomingViewModel
import com.diegolima.TheMoviesTMDb.ui.nowplayingdetail.NowPlayingRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.populardetail.PopularRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.toprateddetail.TopRatedRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.upcomingdetail.UpcomingRecyclerAdapter
import javax.inject.Inject

class MovieListFragment @Inject constructor(
    private val nowPlayingRecyclerAdapter: NowPlayingRecyclerAdapter,
    private val topRatedRecyclerAdapter: TopRatedRecyclerAdapter,
    private val upcomingRecyclerAdapter: UpcomingRecyclerAdapter,
    private val popularRecyclerAdapter: PopularRecyclerAdapter
): Fragment(R.layout.fragment_movie_list) {

    lateinit var viewModelNowPlaying: NowPlayingViewModel
    lateinit var viewModelTopRated: TopRatedViewModel
    lateinit var viewModelUpcoming: UpcomingViewModel
    lateinit var viewModelPopular: PopularViewModel

    private var _binding: FragmentMovieListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelNowPlaying = ViewModelProvider(requireActivity()).get(NowPlayingViewModel::class.java)
        viewModelTopRated = ViewModelProvider(requireActivity()).get(TopRatedViewModel::class.java)
        viewModelUpcoming = ViewModelProvider(requireActivity()).get(UpcomingViewModel::class.java)
        viewModelPopular = ViewModelProvider(requireActivity()).get(PopularViewModel::class.java)

        val binding = FragmentMovieListBinding.bind(view)
        _binding = binding

        viewModelNowPlaying.getMoviesNowPlaying()
        viewModelTopRated.getMoviesTopRated()
        viewModelUpcoming.getMoviesUpcoming()
        viewModelPopular.getMoviesPopular()

        subscribeToObservers()

        binding.rvNowPlaying.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvNowPlaying.adapter = nowPlayingRecyclerAdapter

        binding.rvTopRated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRated.adapter = topRatedRecyclerAdapter

        binding.rvUpcoming.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcoming.adapter = upcomingRecyclerAdapter

        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopular.adapter = popularRecyclerAdapter

        nowPlayingRecyclerAdapter.setOnMovieItemClickListener { movieNowPlayingClick ->
            viewModelNowPlaying.movie = movieNowPlayingClick
            findNavController().navigate(R.id.action_movieListFragment_to_nowPlayingDetailFragment)
        }

        topRatedRecyclerAdapter.setOnMovieItemClickListener { movieTopRatedClick ->
            viewModelTopRated.movie = movieTopRatedClick
            findNavController().navigate(R.id.action_movieListFragment_to_topRatedFragment)
        }

        upcomingRecyclerAdapter.setOnMovieItemClickListener { movieUpcomingClick ->
            viewModelUpcoming.movie = movieUpcomingClick
            findNavController().navigate(R.id.action_movieListFragment_to_upcomingDetailFragment)
        }

        popularRecyclerAdapter.setOnMovieItemClickListener { moviePopularClick ->
            viewModelPopular.movie = moviePopularClick
            findNavController().navigate(R.id.action_movieListFragment_to_popularDetailFragment)
        }

        binding.btnUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_self)
        }
    }

    private fun subscribeToObservers() {
        viewModelNowPlaying.moviesListNowPlaying.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val movies = it.data?.results
                    nowPlayingRecyclerAdapter.movies = movies!!
                    visibilityComponents(1, 0, 0)
                }
                Status.ERROR -> {
                    visibilityComponents(0, 0, 1)
                }
                Status.LOADING -> {
                    visibilityComponents(0, 1, 0)
                }
            }
        })

        viewModelTopRated.moviesListTopRated.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val movies = it.data?.results
                    topRatedRecyclerAdapter.movies = movies!!
                    visibilityComponents(1, 0, 0)
                }
                Status.ERROR -> {
                    visibilityComponents(0, 0, 1)
                }
                Status.LOADING -> {
                    visibilityComponents(0, 1, 0)
                }
            }
        })

        viewModelUpcoming.moviesListUpcoming.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val movies = it.data?.results
                    upcomingRecyclerAdapter.movies = movies!!
                    visibilityComponents(1, 0, 0)
                }
                Status.ERROR -> {
                    visibilityComponents(0, 0, 1)
                }
                Status.LOADING -> {
                    visibilityComponents(0, 1, 0)
                }
            }
        })

        viewModelPopular.moviesListPopular.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val movies = it.data?.results
                    popularRecyclerAdapter.movies = movies!!
                    visibilityComponents(1, 0, 0)
                }
                Status.ERROR -> {
                    visibilityComponents(0, 0, 1)

                }
                Status.LOADING -> {
                    visibilityComponents(0, 1, 0)
                }
            }
        })
    }

    fun visibilityComponents(
        rvNowPlaying: Int,
        pbLoadingMoviesList: Int,
        textMessage: Int
    ) {
        if (pbLoadingMoviesList == 1) {
            _binding?.LayoutMovieList?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            _binding?.pbLoadingMoviesList?.visibility = View.VISIBLE
            _binding?.ivImgError?.visibility = View.INVISIBLE
            _binding?.tvMsgErrorDescription?.visibility = View.INVISIBLE
            _binding?.tvMsgErrorTitle?.visibility = View.INVISIBLE
            _binding?.btnUpdate?.visibility = View.INVISIBLE
            _binding?.ivLogo?.visibility = View.VISIBLE
            _binding?.tvNowPlaying?.visibility = View.INVISIBLE
            _binding?.rvNowPlaying?.visibility = View.INVISIBLE
            _binding?.tvTopRated?.visibility = View.INVISIBLE
            _binding?.rvTopRated?.visibility = View.INVISIBLE
            _binding?.tvUpcoming?.visibility = View.INVISIBLE
            _binding?.rvUpcoming?.visibility = View.INVISIBLE
            _binding?.tvPopular?.visibility = View.INVISIBLE
            _binding?.rvPopular?.visibility = View.INVISIBLE
        } else if (rvNowPlaying == 1) {
            _binding?.LayoutMovieList?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            _binding?.pbLoadingMoviesList?.visibility = View.INVISIBLE
            _binding?.ivImgError?.visibility = View.INVISIBLE
            _binding?.tvMsgErrorDescription?.visibility = View.INVISIBLE
            _binding?.tvMsgErrorTitle?.visibility = View.INVISIBLE
            _binding?.btnUpdate?.visibility = View.INVISIBLE
            _binding?.ivLogo?.visibility = View.VISIBLE
            _binding?.tvNowPlaying?.visibility = View.VISIBLE
            _binding?.rvNowPlaying?.visibility = View.VISIBLE
            _binding?.tvTopRated?.visibility = View.VISIBLE
            _binding?.rvTopRated?.visibility = View.VISIBLE
            _binding?.tvUpcoming?.visibility = View.VISIBLE
            _binding?.rvUpcoming?.visibility = View.VISIBLE
            _binding?.tvPopular?.visibility = View.VISIBLE
            _binding?.rvPopular?.visibility = View.VISIBLE
        }else if(textMessage == 1){
            _binding?.LayoutMovieList?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorLightMediumGray))
            _binding?.tvMsgErrorDescription?.text = resources.getText(R.string.textDescrition)
            _binding?.tvMsgErrorTitle?.text = resources.getText(R.string.textTitle)
            _binding?.pbLoadingMoviesList?.visibility = View.INVISIBLE
            _binding?.ivImgError?.visibility = View.VISIBLE
            _binding?.tvMsgErrorDescription?.visibility = View.VISIBLE
            _binding?.tvMsgErrorTitle?.visibility = View.VISIBLE
            _binding?.btnUpdate?.visibility = View.VISIBLE
            _binding?.ivLogo?.visibility = View.INVISIBLE
            _binding?.tvNowPlaying?.visibility = View.INVISIBLE
            _binding?.rvNowPlaying?.visibility = View.INVISIBLE
            _binding?.tvTopRated?.visibility = View.INVISIBLE
            _binding?.rvTopRated?.visibility = View.INVISIBLE
            _binding?.tvUpcoming?.visibility = View.INVISIBLE
            _binding?.rvUpcoming?.visibility = View.INVISIBLE
            _binding?.tvPopular?.visibility = View.INVISIBLE
            _binding?.rvPopular?.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}