package com.murosar.kmp.completemoviesapp.data.database

import com.murosar.kmp.completemoviesapp.data.database.dao.KnownForDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularPersonDao
import com.murosar.kmp.completemoviesapp.data.database.dao.RecommendedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.TopRatedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.UpcomingMovieDao
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseKnownFor
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBasePopularMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBasePopularPerson
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseRecommendedMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseTopRatedMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseUpcomingMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalPopularMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalPopularPersonList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalRecommendedMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalTopRatedMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalUpcomingMovieList
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

class TheMovieDBDatabaseImpl(
    private val popularPersonDao: PopularPersonDao,
    private val knownForDao: KnownForDao,
    private val popularMovieDao: PopularMovieDao,
    private val topRatedMovieDao: TopRatedMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao,
    private val recommendedMovieDao: RecommendedMovieDao,
) : TheMovieDBDatabase {

    override suspend fun insertPopularPersons(popularPersons: List<PopularPerson>) {
        popularPersons.forEach { popularPerson ->
            popularPersonDao.insertPopularPerson(popularPerson.mapToDataBasePopularPerson())

            popularPerson.knownFor.forEach {
                knownForDao.insertKnownFor(it.mapToDataBaseKnownFor(popularPerson.id))
            }
        }
        println("✅ insertPopularPersons into DB, SUCCESS")
    }

    override suspend fun getPopularPersons(): CoroutineResult<List<PopularPerson>> =
        popularPersonDao.getPopularPersons().let {
            if (it.isNotEmpty()) {
                println("✅ getPopularPersons from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalPopularPersonList())
            } else {
                println("⚠️ getPopularPersons from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertPopularMovie(movies: List<Movie>) {
        movies.forEach { movie ->
            popularMovieDao.insertPopularMovie(movie.mapToDataBasePopularMovie())
        }
        println("✅ insertPopularMovie into DB, SUCCESS")
    }

    override suspend fun getPopularMovie(): CoroutineResult<List<Movie>> =
        popularMovieDao.getPopularMovies().let {
            if (it.isNotEmpty()) {
                println("✅ getPopularPersons from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalPopularMovieList())
            } else {
                println("⚠️ getPopularMovie from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertTopRatedMovie(movies: List<Movie>) {
        movies.forEach { movie ->
            topRatedMovieDao.insertTopRatedMovie(movie.mapToDataBaseTopRatedMovie())
        }
        println("✅ insertTopRatedMovie into DB, SUCCESS")
    }

    override suspend fun getTopRatedMovie(): CoroutineResult<List<Movie>> =
        topRatedMovieDao.getTopRatedMovies().let {
            if (it.isNotEmpty()) {
                println("✅ getPopularPersons from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalTopRatedMovieList())
            } else {
                println("⚠️ getTopRatedMovie from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertUpcomingMovie(movies: List<Movie>) {
        movies.forEach { movie ->
            upcomingMovieDao.insertUpcomingMovie(movie.mapToDataBaseUpcomingMovie())
        }
        println("✅ insertUpcomingMovie into DB, SUCCESS")
    }

    override suspend fun getUpcomingMovie(): CoroutineResult<List<Movie>> =
        upcomingMovieDao.getUpcomingMovies().let {
            if (it.isNotEmpty()) {
                println("✅ getPopularPersons from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalUpcomingMovieList())
            } else {
                println("⚠️ getUpcomingMovie from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertRecommendedMovies(movieId: Int, movies: List<Movie>) {
        movies.forEach { movie ->
            recommendedMovieDao.insertRecommendedMovie(movie.mapToDataBaseRecommendedMovie(movieId))
        }
        println("✅ insertRecommendedMovies into DB, SUCCESS")
    }

    override suspend fun getRecommendedMoviesById(movieId: Int): CoroutineResult<List<Movie>> =
        recommendedMovieDao.getRecommendedMoviesById(movieId).let {
            if (it.isNotEmpty()) {
                println("✅ getPopularPersons from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalRecommendedMovieList())
            } else {
                println("⚠️ getRecommendedMoviesById from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

//    override suspend fun insertMovieDetail(movieId: Int, movieDetail: Movie) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getMovieDetailById(movieId: Int): CoroutineResult<Movie> {
//        TODO("Not yet implemented")
//    }
}
