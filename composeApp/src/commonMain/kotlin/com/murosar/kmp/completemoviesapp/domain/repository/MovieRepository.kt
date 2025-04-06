package com.murosar.kmp.completemoviesapp.domain.repository

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

interface MovieRepository {
    suspend fun getPopularMovieList(): CoroutineResult<List<Movie>>
    suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>>
    suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>>
    suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>>
    suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail>
    suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection>
}
