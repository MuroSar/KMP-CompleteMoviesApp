package com.murosar.kmp.completemoviesapp.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class GetMovieCollectionByNameUseCaseImplTest {

    private val repository = mock(MovieRepository::class)
    private val movieCollection = MovieCollection(
        id = 6655,
        adult = false,
        backdropPath = "fusce",
        name = "Sandra Serrano",
        originalLanguage = "eloquentiam",
        originalName = "Shawn Gordon",
        overview = "expetenda",
        posterPath = "tibique"
    )

    private lateinit var getMovieCollectionByNameUseCase: GetMovieCollectionByNameUseCase

    @BeforeTest
    fun setup() {
        getMovieCollectionByNameUseCase = GetMovieCollectionByNameUseCaseImpl(repository)
    }

    @Test
    fun `should return the collection from repository`() = runTest {
        coEvery { repository.getMovieCollectionByName("SciFi") }.returns(CoroutineResult.Success(movieCollection))

        val result = getMovieCollectionByNameUseCase("SciFi")

        assertThat(result).isInstanceOf<CoroutineResult.Success<MovieCollection>>()
        assertThat((result as CoroutineResult.Success<MovieCollection>).data).isEqualTo(movieCollection)

        coVerify { repository.getMovieCollectionByName("SciFi") }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return SerializationError from repository`() = runTest {
        coEvery { repository.getMovieCollectionByName("SciFi") }.returns(CoroutineResult.Failure(MovieError.SerializationError))

        val result = getMovieCollectionByNameUseCase("SciFi")

        assertThat(result).isInstanceOf<CoroutineResult.Failure>()
        assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

        coVerify { repository.getMovieCollectionByName("SciFi") }.wasInvoked(exactly = 1)
    }

}
