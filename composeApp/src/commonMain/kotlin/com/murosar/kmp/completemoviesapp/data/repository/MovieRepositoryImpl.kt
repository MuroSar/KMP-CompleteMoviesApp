package com.murosar.kmp.completemoviesapp.data.repository

import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class MovieRepositoryImpl(
    private val theMovieDBDataSource: TheMovieDBDataSource,
    private val theMovieDBDatabase: TheMovieDBDatabase,
) : MovieRepository {
    override suspend fun getPopularMovieList(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = theMovieDBDataSource.getPopularMovieList()) {
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
    }

    override suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = theMovieDBDataSource.getTopRatedMovieList()) {
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
    }

    override suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>> {
        return when (val serviceResult = theMovieDBDataSource.getUpcomingMovieList()) {
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
    }

    override suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>> {
        return when (val serviceResult = theMovieDBDataSource.getRecommendedMoviesListById(movieId)) {
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
    }

    override suspend fun getMovieDetail(movieId: Int): CoroutineResult<Movie> {
        return theMovieDBDataSource.getMovieDetail(movieId)
//        return when (val serviceResult = theMovieDBDataSource.getMovieDetail(movieId)) {
//            is CoroutineResult.Success -> {
//                println("✅ getMovieDetail SUCCESS, inserting into database")
//                theMovieDBDatabase.insertMovieDetail(movieId, serviceResult.data)
//                serviceResult
//            }
//
//            is CoroutineResult.Failure -> {
//                println("⚠️ getMovieDetail FAILS, trying to get from database")
//                theMovieDBDatabase.getMovieDetailById(movieId)
//            }
//        }
    }
}
