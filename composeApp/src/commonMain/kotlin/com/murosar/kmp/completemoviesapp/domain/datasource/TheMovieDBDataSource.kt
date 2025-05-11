package com.murosar.kmp.completemoviesapp.domain.datasource

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
interface TheMovieDBDataSource {
    suspend fun getPopularPersonList(): CoroutineResult<List<PopularPerson>>

    suspend fun getPopularMovieList(): CoroutineResult<List<Movie>>

    suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>>

    suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>>

    suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail>

    suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection>

    suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>>
}
