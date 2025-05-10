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

class GetPopularRecommendedListUseCaseTest {

    private val repository = mock(MovieRepository::class)

    private lateinit var getRecommendedMoviesListByIdUseCase: GetRecommendedMoviesListByIdUseCase

    @BeforeTest
    fun setup() {
        getRecommendedMoviesListByIdUseCase = GetRecommendedMoviesListByIdUseCaseImpl(repository)
    }

    @Test
    fun `should return recommendedMovies from repository`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { repository.getRecommendedMoviesListById(MOVIE_ID) }.returns(CoroutineResult.Success(recommendedMovies))

        val result = getRecommendedMoviesListByIdUseCase(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(recommendedMovies)

        coVerify { repository.getRecommendedMoviesListById(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return SerializationError from repository`() = runTest {
        coEvery { repository.getRecommendedMoviesListById(MOVIE_ID) }.returns(CoroutineResult.Failure(MovieError.SerializationError))

        val result = getRecommendedMoviesListByIdUseCase(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Failure>()
        assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

        coVerify { repository.getRecommendedMoviesListById(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    companion object {
        private const val MOVIE_ID = 9292
    }
}
