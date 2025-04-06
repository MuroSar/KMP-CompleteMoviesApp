package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieCollectionByNameUseCase
import com.murosar.kmp.completemoviesapp.domain.usecase.GetMovieDetailByIdUseCase
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
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase,
    private val getRecommendedMoviesListByIdUseCase: GetRecommendedMoviesListByIdUseCase,
    private val getMovieCollectionByNameUseCase: GetMovieCollectionByNameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val uiState: StateFlow<MovieDetailState> = _uiState

    fun fetchMovieDetail(movieId: Int) = viewModelScope.launch {
        withContext(dispatcher) {
            _uiState.update { MovieDetailState.Loading }
            coroutineScope {
                val movieDetailDeferred = async { getMovieDetailByIdUseCase(movieId) }
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
                } else if (movieDetail == null) {
                    _uiState.update { MovieDetailState.Error(MovieError.UnknownError()) }
                } else {
                    when(val collectionResponse = getMovieCollectionByNameUseCase(movieDetail.belongsToCollection?.name.orEmpty())) {
                        is CoroutineResult.Success -> {
                            _uiState.update {
                                MovieDetailState.ShowMovieDetail(
                                    movieDetail = movieDetail,
                                    movieCollection = collectionResponse.data,
                                    recommendedMovieList = recommendedMovies,
                                )
                            }
                        }
                        is CoroutineResult.Failure -> {
                            _uiState.update {
                                MovieDetailState.ShowMovieDetail(
                                    movieDetail = movieDetail,
                                    recommendedMovieList = recommendedMovies,
                                )
                            }
                        }
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
        data class ShowMovieDetail(
            val movieDetail: MovieDetail,
            val movieCollection: MovieCollection? = null,
            val recommendedMovieList: List<Movie>,
        ) : MovieDetailState()

        data class Error(val movieError: MovieError) : MovieDetailState()
    }
}
