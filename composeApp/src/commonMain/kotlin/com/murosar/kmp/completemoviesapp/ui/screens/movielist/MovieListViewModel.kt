package com.murosar.kmp.completemoviesapp.ui.screens.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularMovieListUseCase

import com.murosar.kmp.completemoviesapp.domain.usecase.GetTopRatedMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetUpcomingMovieListUseCase
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val getPopularMovieListUseCase: GetPopularMovieListUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListState>(MovieListState.Idle)
    val uiState: StateFlow<MovieListState> = _uiState

    fun fetchMovies() = viewModelScope.launch {
        withContext(dispatcher) {
            _uiState.update { MovieListState.Loading }

            coroutineScope {
                val popularMoviesDeferred = async { getPopularMovieListUseCase() }
                val topRatedMoviesDeferred = async { getTopRatedMovieListUseCase() }
                val upcomingMoviesDeferred = async { getUpcomingMovieListUseCase() }

                val errors = mutableListOf<MovieError>()

                val popularMovies = when (val result = popularMoviesDeferred.await()) {
                    is CoroutineResult.Success -> result.data
                    is CoroutineResult.Failure -> {
                        logError("Popular Movies", result.error)
                        errors.add(result.error)
                        emptyList()
                    }
                }

                val topRatedMovies = when (val result = topRatedMoviesDeferred.await()) {
                    is CoroutineResult.Success -> result.data
                    is CoroutineResult.Failure -> {
                        logError("Top Rated Movies", result.error)
                        errors.add(result.error)
                        emptyList()
                    }
                }

                val upcomingMovies = when (val result = upcomingMoviesDeferred.await()) {
                    is CoroutineResult.Success -> result.data
                    is CoroutineResult.Failure -> {
                        logError("Upcoming Movies", result.error)
                        errors.add(result.error)
                        emptyList()
                    }
                }

                // If all errors are of the same type (i.e. No Internet), we return the error
                val commonError = errors.distinct().singleOrNull()
                if (commonError != null) {
                    _uiState.update { MovieListState.Error(commonError) }
                } else {
                    _uiState.update {
                        MovieListState.Success(
                            popularMovieList = popularMovies,
                            topRatedMovieList = topRatedMovies,
                            upcomingMovieList = upcomingMovies
                        )
                    }
                }
            }
        }
    }

    private fun logError(category: String, error: MovieError) {
        println("❌ Error fetching $category: ${error.message} (Code: ${error.code})")
    }

    sealed class MovieListState {
        data object Idle : MovieListState()
        data object Loading : MovieListState()
        data class Success(
            val popularMovieList: List<Movie>,
            val topRatedMovieList: List<Movie>,
            val upcomingMovieList: List<Movie>,
        ) : MovieListState()

        data class Error(val movieError: MovieError) : MovieListState()
    }
}
