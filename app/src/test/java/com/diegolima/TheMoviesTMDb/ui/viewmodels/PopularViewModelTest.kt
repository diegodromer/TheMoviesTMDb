package com.diegolima.TheMoviesTMDb.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.diegolima.TheMoviesTMDb.MainCoroutineRule
import com.diegolima.TheMoviesTMDb.domain.network.MockMovieRepositories
import com.diegolima.TheMoviesTMDb.ui.populardetail.PopularViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PopularViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutineRule()

    private lateinit var popularViewModel: PopularViewModel

    @Before
    fun setup(){
        popularViewModel = PopularViewModel(MockMovieRepositories())
    }

    @Test
    fun `check if is page 1 from getMovies api`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.page).isEqualTo(2)
    }

    @Test
    fun `Test receive correct poster_path`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.poster_path).isEqualTo("/zERcfV2rSHNhW5eViQiO9hNiA7.jpg")
    }

    @Test
    fun `Test receive correct adult`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.adult).isEqualTo(true)
    }

    @Test
    fun `Test receive correct overview`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.overview).isEqualTo("Um jovem brilhante e talentoso com um grande destino para além da sua compreensão, que tem de viajar para o planeta mais perigoso do universo para garantir o futuro da sua família e do seu povo. Quando forças malévolas entram em conflito para obter uma quantidade exclusiva do recurso mais precioso do planeta – uma substância capaz de desbloquear o maior potencial da humanidade – apenas os que conquistam os seus medos conseguiram sobreviver.")
    }

    @Test
    fun `Test receive correct release_date`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.release_date).isEqualTo("2021-09-01")
    }

    @Test
    fun `Test receive correct size genre_data`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.genre_ids?.size).isEqualTo(2)
    }

    @Test
    fun `Test receive correct items genre_data`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.genre_ids).containsAnyOf(878, 12)
    }

    @Test
    fun `Test receive correct id`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.id).isEqualTo(438631)
    }


    @Test
    fun `Test receive correct original_title`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.original_title).isEqualTo("Venon")
    }

    @Test
    fun `Test receive correct original_language`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.original_language).isEqualTo("en")
    }

    @Test
    fun `Test receive correct title name`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.title).isEqualTo("Venon")
    }

    @Test
    fun `Test receive correct backdrop_path`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.backdrop_path).isEqualTo("/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg")
    }

    @Test
    fun `Test receive correct popularity`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.popularity).isEqualTo(2478.539)
    }

    @Test
    fun `Test receive correct vote_count`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.video).isEqualTo(false)
    }

    @Test
    fun `Test receive correct vote_average`(){
        popularViewModel.getMoviesPopular()
        val value = popularViewModel.moviesListPopular
        assertThat(value.value?.data?.results?.get(0)?.vote_average).isEqualTo(10.0)
    }

}