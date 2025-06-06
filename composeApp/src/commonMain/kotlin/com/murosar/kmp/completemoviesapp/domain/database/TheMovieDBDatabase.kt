package com.murosar.kmp.completemoviesapp.domain.database

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.Mockable

@Mockable
interface TheMovieDBDatabase {
    suspend fun insertPopularPersons(popularPersons: List<PopularPerson>)

    suspend fun getPopularPersons(): CoroutineResult<List<PopularPerson>>

    suspend fun insertPopularMovie(movies: List<Movie>)

    suspend fun getPopularMovie(): CoroutineResult<List<Movie>>

    suspend fun insertTopRatedMovie(movies: List<Movie>)

    suspend fun getTopRatedMovie(): CoroutineResult<List<Movie>>

    suspend fun insertUpcomingMovie(movies: List<Movie>)

    suspend fun getUpcomingMovie(): CoroutineResult<List<Movie>>

    suspend fun insertRecommendedMovies(
        movieId: Int,
        movies: List<Movie>,
    )

    suspend fun getRecommendedMoviesById(movieId: Int): CoroutineResult<List<Movie>>

    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail>

    suspend fun insertMovieCollection(movieCollection: MovieCollection)

    suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection>
}
