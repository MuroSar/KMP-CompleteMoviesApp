package com.murosar.kmp.completemoviesapp.data.repository

import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class MovieRepositoryImpl(
    private val theMovieDBDataSource: TheMovieDBDataSource
): MovieRepository {
    override suspend fun getPopularMovieList(): CoroutineResult<List<Movie>> {
        return theMovieDBDataSource.getPopularMovieList()
    }

    override suspend fun getTopRatedMovieList(): CoroutineResult<List<Movie>> {
        return theMovieDBDataSource.getTopRatedMovieList()
    }

    override suspend fun getRecommendedMoviesListById(movieId: Int): CoroutineResult<List<Movie>> {
        return theMovieDBDataSource.getRecommendedMoviesListById(movieId)
    }

    override suspend fun getMovieDetail(movieId: Int): CoroutineResult<Movie> {
        return theMovieDBDataSource.getMovieDetail(movieId)
    }

    override suspend fun getUpcomingMovieList(): CoroutineResult<List<Movie>> {
        return theMovieDBDataSource.getUpcomingMovieList()
    }
}
