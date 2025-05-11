package com.murosar.kmp.completemoviesapp.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetMovieDetailByIdUseCaseTest {
    private val repository = mock(MovieRepository::class)

    private lateinit var getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase

    @BeforeTest
    fun setup() {
        getMovieDetailByIdUseCase = GetMovieDetailByIdUseCaseImpl(repository)
    }

    @Test
    fun `should return the details from repository`() =
        runTest {
            coEvery { repository.getMovieDetailById(MOVIE_ID) }.returns(CoroutineResult.Success(movieDetail))

            val result = getMovieDetailByIdUseCase(MOVIE_ID)

            assertThat(result).isInstanceOf<CoroutineResult.Success<MovieDetail>>()
            assertThat((result as CoroutineResult.Success<MovieDetail>).data).isEqualTo(movieDetail)

            coVerify { repository.getMovieDetailById(MOVIE_ID) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `should return SerializationError from repository`() =
        runTest {
            coEvery { repository.getMovieDetailById(MOVIE_ID) }.returns(CoroutineResult.Failure(MovieError.SerializationError))

            val result = getMovieDetailByIdUseCase(MOVIE_ID)

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

            coVerify { repository.getMovieDetailById(MOVIE_ID) }.wasInvoked(exactly = 1)
        }

    companion object {
        private const val MOVIE_ID = 9292

        private val movieDetail =
            MovieDetail(
                id = 6761,
                adult = false,
                backdropPath = "consul",
                belongsToCollection = null,
                budget = 3874,
                genres = listOf(),
                homepage = "alia",
                imdbId = "dolores",
                originCountry = "Vanuatu",
                originalLanguage = "vim",
                originalTitle = "saepe",
                overview = "dictas",
                popularity = 12.13,
                posterPath = "mediocritatem",
                productionCompanies = listOf(),
                productionCountries = listOf(),
                releaseDate = "vocibus",
                revenue = 8223,
                runtime = 7233,
                spokenLanguages = listOf(),
                status = "prompta",
                tagline = "nascetur",
                title = "himenaeos",
                video = false,
                voteAverage = 14.15,
                voteCount = 9150,
            )
    }
}
