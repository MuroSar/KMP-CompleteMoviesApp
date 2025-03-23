package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieDetailUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetRecommendedMoviesListByIdUseCase
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getRecommendedMoviesListByIdUseCase: GetRecommendedMoviesListByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val uiState: StateFlow<MovieDetailState> = _uiState

    fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
        withContext(dispatcher) {
            _uiState.update { MovieDetailState.Loading }
            coroutineScope {
                val movieDetailDeferred = async { getMovieDetailUseCase(movieId) }
                val recommendedMoviesDeferred = async { getRecommendedMoviesListByIdUseCase(movieId) }

                val errors = mutableListOf<MovieError>()

                val movieDetail = when (val result = movieDetailDeferred.await()) {
                    is CoroutineResult.Success -> result.data
                    is CoroutineResult.Failure -> {
                        logError("Popular Movies", result.error)
                        errors.add(result.error)
                        null
                    }
                }

                val recommendedMovies = when (val result = recommendedMoviesDeferred.await()) {
                    is CoroutineResult.Success -> result.data
                    is CoroutineResult.Failure -> {
                        logError("Top Rated Movies", result.error)
                        errors.add(result.error)
                        emptyList()
                    }
                }

                // If all errors are of the same type (i.e. No Internet), we return the error
                val commonError = errors.distinct().singleOrNull()
                if (commonError != null) {
                    _uiState.update { MovieDetailState.Error(commonError) }
                } else {
                    _uiState.update {
                        MovieDetailState.Success(
                            movieDetail = movieDetail,
                            recommendedMovieList = recommendedMovies,
                        )
                    }
                }
            }
        }
    }

    private fun logError(category: String, error: MovieError) {
        println("❌ Error fetching $category: ${error.message} (Code: ${error.code})")
    }

    sealed class MovieDetailState {
        data object Idle : MovieDetailState()
        data object Loading : MovieDetailState()
        data class Success(
            val movieDetail: Movie?,
            val recommendedMovieList: List<Movie>,
        ) : MovieDetailState()

        data class Error(val movieError: MovieError) : MovieDetailState()
    }
}
