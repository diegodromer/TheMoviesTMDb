package com.diegolima.TheMoviesTMDb.core

import com.diegolima.TheMoviesTMDb.domain.network.MovieRepository
import com.diegolima.TheMoviesTMDb.domain.network.MovieRepositoryInterface
import com.diegolima.TheMoviesTMDb.domain.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMovieRepository(movieService: MovieService) = MovieRepository(movieService) as MovieRepositoryInterface
}