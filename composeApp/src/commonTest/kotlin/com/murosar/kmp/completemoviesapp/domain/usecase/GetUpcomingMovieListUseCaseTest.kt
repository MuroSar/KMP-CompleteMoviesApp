package com.murosar.kmp.completemoviesapp.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class GetUpcomingMovieListUseCaseTest {

    private val repository = mock(MovieRepository::class)

    private lateinit var getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase

    @BeforeTest
    fun setup() {
        getUpcomingMovieListUseCase = GetUpcomingMovieListUseCaseImpl(repository)
    }

    @Test
    fun `should return upcomingMovies from repository`() = runTest {
        val upcomingMovies = listOf<Movie>()

        coEvery { repository.getUpcomingMovieList() }.returns(CoroutineResult.Success(upcomingMovies))

        val result = getUpcomingMovieListUseCase()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(upcomingMovies)

        coVerify { repository.getUpcomingMovieList() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return SerializationError from repository`() = runTest {
        coEvery { repository.getUpcomingMovieList() }.returns(CoroutineResult.Failure(MovieError.SerializationError))

        val result = getUpcomingMovieListUseCase()

        assertThat(result).isInstanceOf<CoroutineResult.Failure>()
        assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

        coVerify { repository.getUpcomingMovieList() }.wasInvoked(exactly = 1)
    }
}
