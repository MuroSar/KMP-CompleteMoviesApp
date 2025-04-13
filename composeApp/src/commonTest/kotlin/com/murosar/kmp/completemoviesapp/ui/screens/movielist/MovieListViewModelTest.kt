package com.murosar.kmp.completemoviesapp.ui.screens.movielist

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetTopRatedMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetUpcomingMovieListUseCase
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
class MovieListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getPopularMovieListUseCase = mock(GetPopularMovieListUseCase::class)
    private val getTopRatedMovieListUseCase = mock(GetTopRatedMovieListUseCase::class)
    private val getUpcomingMovieListUseCase = mock(GetUpcomingMovieListUseCase::class)

    private lateinit var viewModel: MovieListViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieListViewModel(
            dispatcher = testDispatcher,
            getPopularMovieListUseCase = getPopularMovieListUseCase,
            getTopRatedMovieListUseCase = getTopRatedMovieListUseCase,
            getUpcomingMovieListUseCase = getUpcomingMovieListUseCase,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when all use cases return Success`() = runTest {
        val popularMovies = listOf<Movie>()
        val topRatedMovies = listOf<Movie>()
        val upcomingMovies = listOf<Movie>()
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Success(popularMovies)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Success(topRatedMovies)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Success(upcomingMovies)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = popularMovies,
                    topRatedMovieList = topRatedMovies,
                    upcomingMovieList = upcomingMovies
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return Error when all use cases return the same error`() = runTest {
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.Error(MovieError.SerializationError)
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when all use cases return different errors`() = runTest {
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Failure(MovieError.DataBaseError)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Failure(MovieError.NoInternet)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = emptyList(),
                    topRatedMovieList = emptyList(),
                    upcomingMovieList = emptyList(),
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when getPopularMovieListUseCase returns error`() = runTest {
        val topRatedMovies = listOf<Movie>()
        val upcomingMovies = listOf<Movie>()
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Success(topRatedMovies)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Success(upcomingMovies)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = emptyList(),
                    topRatedMovieList = topRatedMovies,
                    upcomingMovieList = upcomingMovies
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when getTopRatedMovieListUseCase returns error`() = runTest {
        val popularMovies = listOf<Movie>()
        val upcomingMovies = listOf<Movie>()
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Success(popularMovies)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Success(upcomingMovies)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = popularMovies,
                    topRatedMovieList = emptyList(),
                    upcomingMovieList = upcomingMovies
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when getUpcomingMovieListUseCase returns error`() = runTest {
        val popularMovies = listOf<Movie>()
        val topRatedMovies = listOf<Movie>()
        coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Success(popularMovies)
        coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Success(topRatedMovies)
        coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)

        viewModel.uiState.test {
            viewModel.fetchMovies()

            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = popularMovies,
                    topRatedMovieList = topRatedMovies,
                    upcomingMovieList = emptyList()
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
        coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `fetchMovies should return ShowMovieLists when getPopularMovieListUseCase and getTopRatedMovieListUseCase returns error`() =
        runTest {
            val upcomingMovies = listOf<Movie>()
            coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
            coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
            coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Success(upcomingMovies)

            viewModel.uiState.test {
                viewModel.fetchMovies()

                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
                assertThat(awaitItem()).isEqualTo(
                    MovieListViewModel.MovieListState.ShowMovieLists(
                        popularMovieList = emptyList(),
                        topRatedMovieList = emptyList(),
                        upcomingMovieList = upcomingMovies
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }

            coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
        }

    @Test
    fun `fetchMovies should return ShowMovieLists when getPopularMovieListUseCase and getUpcomingMovieListUseCase returns error`() =
        runTest {
            val topRatedMovies = listOf<Movie>()
            coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
            coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Success(topRatedMovies)
            coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)

            viewModel.uiState.test {
                viewModel.fetchMovies()

                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
                assertThat(awaitItem()).isEqualTo(
                    MovieListViewModel.MovieListState.ShowMovieLists(
                        popularMovieList = emptyList(),
                        topRatedMovieList = topRatedMovies,
                        upcomingMovieList = emptyList()
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }

            coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
        }

    @Test
    fun `fetchMovies should return ShowMovieLists when getTopRatedMovieListUseCase and getUpcomingMovieListUseCase returns error`() =
        runTest {
            val popularMovies = listOf<Movie>()
            coEvery { getPopularMovieListUseCase() } returns CoroutineResult.Success(popularMovies)
            coEvery { getTopRatedMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)
            coEvery { getUpcomingMovieListUseCase() } returns CoroutineResult.Failure(MovieError.SerializationError)

            viewModel.uiState.test {
                viewModel.fetchMovies()

                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Idle>()
                assertThat(awaitItem()).isInstanceOf<MovieListViewModel.MovieListState.Loading>()
                assertThat(awaitItem()).isEqualTo(
                    MovieListViewModel.MovieListState.ShowMovieLists(
                        popularMovieList = popularMovies,
                        topRatedMovieList = emptyList(),
                        upcomingMovieList = emptyList()
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }

            coVerify { getPopularMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getTopRatedMovieListUseCase() }.wasInvoked(exactly = 1)
            coVerify { getUpcomingMovieListUseCase() }.wasInvoked(exactly = 1)
        }
}
