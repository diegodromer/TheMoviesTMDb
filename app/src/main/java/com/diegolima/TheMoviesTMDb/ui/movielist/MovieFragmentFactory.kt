package com.diegolima.TheMoviesTMDb.ui.movielist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.diegolima.TheMoviesTMDb.ui.nowplayingdetail.NowPlayingDetailFragment
import com.diegolima.TheMoviesTMDb.ui.populardetail.PopularDetailFragment
import com.diegolima.TheMoviesTMDb.ui.toprateddetail.TopRatedDetailFragment
import com.diegolima.TheMoviesTMDb.ui.upcomingdetail.UpcomingDetailFragment
import com.diegolima.TheMoviesTMDb.ui.nowplayingdetail.NowPlayingRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.populardetail.PopularRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.toprateddetail.TopRatedRecyclerAdapter
import com.diegolima.TheMoviesTMDb.ui.upcomingdetail.UpcomingRecyclerAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val nowPlayingRecyclerAdapter: NowPlayingRecyclerAdapter,
    private val topRatedRecyclerAdapter: TopRatedRecyclerAdapter,
    private val upcomingRecyclerAdapter: UpcomingRecyclerAdapter,
    private val popularRecyclerAdapter: PopularRecyclerAdapter,
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            MovieListFragment::class.java.name -> MovieListFragment(
                nowPlayingRecyclerAdapter,
                topRatedRecyclerAdapter,
                upcomingRecyclerAdapter,
                popularRecyclerAdapter
            )

            NowPlayingDetailFragment::class.java.name -> NowPlayingDetailFragment(
                glide,
                nowPlayingRecyclerAdapter
            )
            TopRatedDetailFragment::class.java.name -> TopRatedDetailFragment(
                glide,
                topRatedRecyclerAdapter
            )
            UpcomingDetailFragment::class.java.name -> UpcomingDetailFragment(
                glide,
                upcomingRecyclerAdapter
            )
            PopularDetailFragment::class.java.name -> PopularDetailFragment(
                glide,
                popularRecyclerAdapter
            )

            else -> super.instantiate(classLoader, className)
        }
    }
}