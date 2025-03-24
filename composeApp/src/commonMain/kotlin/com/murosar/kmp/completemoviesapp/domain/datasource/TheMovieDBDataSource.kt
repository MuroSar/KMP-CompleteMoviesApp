package com.murosar.kmp.completemoviesapp.domain.datasource

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

interface TheMovieDBDataSource {
    suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>>

    suspend fun getPopularMovieList(): CoroutineResult<List<Movie>>
    suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>>
    suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): CoroutineResult<MovieDetail>
    suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>>
}
