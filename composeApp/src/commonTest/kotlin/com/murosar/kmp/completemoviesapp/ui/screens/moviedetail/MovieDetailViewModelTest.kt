package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieCollectionByNameUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieDetailByIdUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetRecommendedMoviesListByIdUseCase
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getMovieDetailByIdUseCase = mock(GetMovieDetailByIdUseCase::class)
    private val getRecommendedMoviesListByIdUseCase = mock(GetRecommendedMoviesListByIdUseCase::class)
    private val getMovieCollectionByNameUseCase = mock(GetMovieCollectionByNameUseCase::class)

    private lateinit var viewModel: MovieDetailViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieDetailViewModel(
            dispatcher = testDispatcher,
            getMovieDetailByIdUseCase = getMovieDetailByIdUseCase,
            getRecommendedMoviesListByIdUseCase = getRecommendedMoviesListByIdUseCase,
            getMovieCollectionByNameUseCase = getMovieCollectionByNameUseCase,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMovieDetail should return information when all use cases return Success`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { getMovieDetailByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(movieDetail)
        coEvery { getRecommendedMoviesListByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(recommendedMovies)
        coEvery { getMovieCollectionByNameUseCase(MOVIE_COLLECTION_NAME) } returns CoroutineResult.Success(movieCollection)

        viewModel.uiState.test {
            viewModel.fetchMovieDetail(MOVIE_ID)

            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                    movieDetail = movieDetail,
                    movieCollection = movieCollection,
                    recommendedMovieList = recommendedMovies
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getMovieDetailByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getRecommendedMoviesListByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getMovieCollectionByNameUseCase(MOVIE_COLLECTION_NAME) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovieDetail should return information when use cases return Success and there are no collection`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { getMovieDetailByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(movieDetailWithoutCollection)
        coEvery { getRecommendedMoviesListByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(recommendedMovies)
        coEvery { getMovieCollectionByNameUseCase(EMPTY_STRING) } returns CoroutineResult.Failure(MovieError.UnknownError())

        viewModel.uiState.test {
            viewModel.fetchMovieDetail(MOVIE_ID)

            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                    movieDetail = movieDetailWithoutCollection,
                    recommendedMovieList = recommendedMovies
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getMovieDetailByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getRecommendedMoviesListByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getMovieCollectionByNameUseCase(EMPTY_STRING) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovieDetail should return information when use cases return Success and getMovieCollectionByNameUseCase returns error`() =
        runTest {
            val recommendedMovies = listOf<Movie>()

            coEvery { getMovieDetailByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(movieDetail)
            coEvery { getRecommendedMoviesListByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(recommendedMovies)
            coEvery { getMovieCollectionByNameUseCase(MOVIE_COLLECTION_NAME) } returns CoroutineResult.Failure(MovieError.UnknownError())

            viewModel.uiState.test {
                viewModel.fetchMovieDetail(MOVIE_ID)

                assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Idle>()
                assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Loading>()
                assertThat(awaitItem()).isEqualTo(
                    MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                        movieDetail = movieDetail,
                        recommendedMovieList = recommendedMovies
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }

            coVerify { getMovieDetailByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
            coVerify { getRecommendedMoviesListByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
            coVerify { getMovieCollectionByNameUseCase(MOVIE_COLLECTION_NAME) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `fetchMovieDetail should return Error when all use cases return the same error`() = runTest {
        coEvery { getMovieDetailByIdUseCase(MOVIE_ID) } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getRecommendedMoviesListByIdUseCase(MOVIE_ID) } returns CoroutineResult.Failure(MovieError.SerializationError)

        viewModel.uiState.test {
            viewModel.fetchMovieDetail(MOVIE_ID)

            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieDetailViewModel.MovieDetailState.Error(MovieError.SerializationError)
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getMovieDetailByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getRecommendedMoviesListByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovieDetail should return Error when getMovieDetailByIdUseCase return error`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { getMovieDetailByIdUseCase(MOVIE_ID) } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getRecommendedMoviesListByIdUseCase(MOVIE_ID) } returns CoroutineResult.Success(recommendedMovies)

        viewModel.uiState.test {
            viewModel.fetchMovieDetail(MOVIE_ID)

            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieDetailViewModel.MovieDetailState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieDetailViewModel.MovieDetailState.Error(MovieError.SerializationError)
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getMovieDetailByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { getRecommendedMoviesListByIdUseCase(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    companion object {
        private const val EMPTY_STRING = ""

        private const val MOVIE_ID = 9292
        private const val MOVIE_COLLECTION_NAME = "The Godfather"

        private val movieDetail = MovieDetail(
            id = 9292,
            adult = false,
            backdropPath = "indoctum",
            belongsToCollection = BelongsToCollection(id = 6767, name = MOVIE_COLLECTION_NAME, posterPath = "novum", backdropPath = "ad"),
            budget = 3757,
            genres = listOf(),
            homepage = "definiebas",
            imdbId = "accumsan",
            originCountry = "Bhutan",
            originalLanguage = "natoque",
            originalTitle = "urbanitas",
            overview = "aenean",
            popularity = 4.5,
            posterPath = "qualisque",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            releaseDate = "feugiat",
            revenue = 5163,
            runtime = 7076,
            spokenLanguages = listOf(),
            status = "ornare",
            tagline = "pharetra",
            title = "id",
            video = false,
            voteAverage = 6.7,
            voteCount = 7910
        )

        private val movieDetailWithoutCollection = MovieDetail(
            id = 9292,
            adult = false,
            backdropPath = "indoctum",
            belongsToCollection = null,
            budget = 3757,
            genres = listOf(),
            homepage = "definiebas",
            imdbId = "accumsan",
            originCountry = "Bhutan",
            originalLanguage = "natoque",
            originalTitle = "urbanitas",
            overview = "aenean",
            popularity = 4.5,
            posterPath = "qualisque",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            releaseDate = "feugiat",
            revenue = 5163,
            runtime = 7076,
            spokenLanguages = listOf(),
            status = "ornare",
            tagline = "pharetra",
            title = "id",
            video = false,
            voteAverage = 6.7,
            voteCount = 7910
        )

        private val movieCollection = MovieCollection(
            id = 5829,
            adult = false,
            backdropPath = "odio",
            name = MOVIE_COLLECTION_NAME,
            originalLanguage = "consetetur",
            originalName = "Genevieve Lawrence",
            overview = "tempus",
            posterPath = "nulla"
        )
    }
}
