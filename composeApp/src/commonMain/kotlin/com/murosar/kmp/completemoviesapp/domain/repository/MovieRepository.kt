package com.murosar.kmp.completemoviesapp.domain.repository

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

interface MovieRepository {
    suspend fun getPopularMovieList(): CoroutineResult<List<Movie>>
    suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>>
    suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>>
    suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): CoroutineResult<Movie>
}
