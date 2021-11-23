package com.diegolima.TheMoviesTMDb.core.network

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diegolima.TheMoviesTMDb.BuildConfig
import com.diegolima.TheMoviesTMDb.R
import com.diegolima.TheMoviesTMDb.domain.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetProvider {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieService{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun providesGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_broken_image)
        )
}