package com.diegolima.TheMoviesTMDb.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.diegolima.TheMoviesTMDb.MainCoroutineRule
import com.diegolima.TheMoviesTMDb.domain.network.MockMovieRepositories
import com.diegolima.TheMoviesTMDb.ui.upcomingdetail.UpcomingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UpcomingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    private lateinit var upcomingViewModel: UpcomingViewModel

    @Before
    fun setup(){
        upcomingViewModel = UpcomingViewModel(MockMovieRepositories())
    }

    @Test
    fun `check if is page 1 from getMovies api`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.page).isEqualTo(1)
    }

    @Test
    fun `Test receive correct poster_path`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.poster_path).isEqualTo("/zERcfV2rSHNhW5eViQiO9hNiA7.jpg")
    }

    @Test
    fun `Test receive correct adult`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.adult).isEqualTo(true)
    }

    @Test
    fun `Test receive correct overview`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.overview).isEqualTo("“Dune - Duna” conta a história de Paul Atreides, um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.")
    }

    @Test
    fun `Test receive correct release_date`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.release_date).isEqualTo("2021-09-10")
    }

    @Test
    fun `Test receive correct size genre_data`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.genre_ids?.size).isEqualTo(2)
    }

    @Test
    fun `Test receive correct items genre_data`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.genre_ids).containsAnyOf(878, 12)
    }

    @Test
    fun `Test receive correct id`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.id).isEqualTo(438631)
    }


    @Test
    fun `Test receive correct original_title`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.original_title).isEqualTo("Filme")
    }

    @Test
    fun `Test receive correct original_language`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.original_language).isEqualTo("en")
    }

    @Test
    fun `Test receive correct title name`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.title).isEqualTo("Filme")
    }

    @Test
    fun `Test receive correct backdrop_path`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.backdrop_path).isEqualTo("/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg")
    }

    @Test
    fun `Test receive correct popularity`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.popularity).isEqualTo(2478.539)
    }

    @Test
    fun `Test receive correct vote_count`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.video).isEqualTo(false)
    }

    @Test
    fun `Test receive correct vote_average`(){
        upcomingViewModel.getMoviesUpcoming()
        val value = upcomingViewModel.moviesListUpcoming
        assertThat(value.value?.data?.results?.get(0)?.vote_average).isEqualTo(10.0)
    }

}