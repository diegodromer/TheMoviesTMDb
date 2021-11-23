package com.diegolima.TheMoviesTMDb.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.diegolima.TheMoviesTMDb.MainCoroutineRule
import com.diegolima.TheMoviesTMDb.domain.network.MockMovieRepositories
import com.diegolima.TheMoviesTMDb.ui.toprateddetail.TopRatedViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TopRatedViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    private lateinit var topRatedViewModel: TopRatedViewModel

    @Before
    fun setup(){
        topRatedViewModel = TopRatedViewModel(MockMovieRepositories())
    }

    @Test
    fun `check if is page 1 from getMovies api`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.page).isEqualTo(9)
    }

    @Test
    fun `Test receive correct poster_path`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.poster_path).isEqualTo("/ERcfV2rSHNhW5eViQiO9hNiA.jpg")
    }

    @Test
    fun `Test receive correct adult`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.adult).isEqualTo(true)
    }

    @Test
    fun `Test receive correct overview`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.overview).isEqualTo("Viagem mítica e emocional, “Dune - Duna” conta a história de Paul Atreides, um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.")
    }

    @Test
    fun `Test receive correct release_date`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.release_date).isEqualTo("2021-09-10")
    }

    @Test
    fun `Test receive correct size genre_data`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.genre_ids?.size).isEqualTo(2)
    }

    @Test
    fun `Test receive correct items genre_data`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.genre_ids).containsAnyOf(878, 12)
    }

    @Test
    fun `Test receive correct id`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.id).isEqualTo(438631)
    }


    @Test
    fun `Test receive correct original_title`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.original_title).isEqualTo("Filme")
    }

    @Test
    fun `Test receive correct original_language`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.original_language).isEqualTo("en")
    }

    @Test
    fun `Test receive correct title name`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.title).isEqualTo("Duna")
    }

    @Test
    fun `Test receive correct backdrop_path`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.backdrop_path).isEqualTo("/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg")
    }

    @Test
    fun `Test receive correct popularity`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.popularity).isEqualTo(2478.539)
    }

    @Test
    fun `Test receive correct vote_count`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.video).isEqualTo(false)
    }

    @Test
    fun `Test receive correct vote_average`(){
        topRatedViewModel.getMoviesTopRated()
        val value = topRatedViewModel.moviesListTopRated
        assertThat(value.value?.data?.results?.get(0)?.vote_average).isEqualTo(10.0)
    }

}