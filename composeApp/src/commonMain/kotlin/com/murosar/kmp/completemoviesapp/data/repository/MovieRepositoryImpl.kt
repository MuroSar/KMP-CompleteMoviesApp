package com.murosar.kmp.completemoviesapp.data.repository

import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class MovieRepositoryImpl(
    private val theMovieDBDataSource: TheMovieDBDataSource,
    private val theMovieDBDatabase: TheMovieDBDatabase,
) : MovieRepository {
    override suspend fun getPopularMovieList(): CoroutineResult<List<Movie>> =
        when (val serviceResult = theMovieDBDataSource.getPopularMovieList()) {
            is CoroutineResult.Success -> {
                println("✅ getPopularMovieList SUCCESS, inserting into database")
                theMovieDBDatabase.insertPopularMovie(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getPopularMovieList FAILS, trying to get from database")
                theMovieDBDatabase.getPopularMovie()
            }
        }

    override suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>> =
        when (val serviceResult = theMovieDBDataSource.getTopRatedMovieList()) {
            is CoroutineResult.Success -> {
                println("✅ getTopRatedMovieList SUCCESS, inserting into database")
                theMovieDBDatabase.insertTopRatedMovie(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getTopRatedMovieList FAILS, trying to get from database")
                theMovieDBDatabase.getTopRatedMovie()
            }
        }

    override suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>> =
        when (val serviceResult = theMovieDBDataSource.getUpcomingMovieList()) {
            is CoroutineResult.Success -> {
                println("✅ getUpcomingMovieList SUCCESS, inserting into database")
                theMovieDBDatabase.insertUpcomingMovie(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getUpcomingMovieList FAILS, trying to get from database")
                theMovieDBDatabase.getUpcomingMovie()
            }
        }

    override suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>> =
        when (val serviceResult = theMovieDBDataSource.getRecommendedMoviesListById(movieId)) {
            is CoroutineResult.Success -> {
                println("✅ getRecommendedMoviesListById SUCCESS, inserting into database")
                theMovieDBDatabase.insertRecommendedMovies(movieId, serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getRecommendedMoviesListById FAILS, trying to get from database")
                theMovieDBDatabase.getRecommendedMoviesById(movieId)
            }
        }

    override suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail> =
        when (val serviceResult = theMovieDBDataSource.getMovieDetailById(movieId)) {
            is CoroutineResult.Success -> {
                println("✅ getMovieDetailById SUCCESS, inserting into database")
                theMovieDBDatabase.insertMovieDetail(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getMovieDetailById FAILS, trying to get from database")
                theMovieDBDatabase.getMovieDetailById(movieId)
            }
        }

    override suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection> =
        when (val serviceResult = theMovieDBDataSource.getMovieCollectionByName(collectionName)) {
            is CoroutineResult.Success -> {
                println("✅ getMovieCollectionByName SUCCESS, inserting into database")
                theMovieDBDatabase.insertMovieCollection(serviceResult.data)
                serviceResult
            }

            is CoroutineResult.Failure -> {
                println("⚠️ getMovieCollectionByName FAILS, trying to get from database")
                theMovieDBDatabase.getMovieCollectionByName(collectionName)
            }
        }
}
