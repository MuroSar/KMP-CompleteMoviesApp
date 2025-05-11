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
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetPopularMovieListUseCaseTest {
    private val repository = mock(MovieRepository::class)

    private lateinit var getPopularMovieListUseCase: GetPopularMovieListUseCase

    @BeforeTest
    fun setup() {
        getPopularMovieListUseCase = GetPopularMovieListUseCaseImpl(repository)
    }

    @Test
    fun `should return popularMovies from repository`() =
        runTest {
            val popularMovies = listOf<Movie>()

            coEvery { repository.getPopularMovieList() }.returns(CoroutineResult.Success(popularMovies))

            val result = getPopularMovieListUseCase()

            assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
            assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(popularMovies)

            coVerify { repository.getPopularMovieList() }.wasInvoked(exactly = 1)
        }

    @Test
    fun `should return SerializationError from repository`() =
        runTest {
            coEvery { repository.getPopularMovieList() }.returns(CoroutineResult.Failure(MovieError.SerializationError))

            val result = getPopularMovieListUseCase()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

            coVerify { repository.getPopularMovieList() }.wasInvoked(exactly = 1)
        }
}
