package com.murosar.kmp.completemoviesapp.data.database

import com.murosar.kmp.completemoviesapp.data.database.dao.KnownForDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieCollectionDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailBelongsToCollectionDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailGenreDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailProductionCompanyDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailProductionCountryDao
import com.murosar.kmp.completemoviesapp.data.database.dao.MovieDetailSpokenLanguageDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.PopularPersonDao
import com.murosar.kmp.completemoviesapp.data.database.dao.RecommendedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.TopRatedMovieDao
import com.murosar.kmp.completemoviesapp.data.database.dao.UpcomingMovieDao
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseKnownFor
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieCollection
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetail
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetailBelongsToCollection
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetailGenre
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetailProductionCompany
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetailProductionCountry
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseMovieDetailSpokenLanguage
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBasePopularMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBasePopularPerson
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseRecommendedMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseTopRatedMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToDataBaseUpcomingMovie
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalMovieCollection
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalMovieDetail
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalPopularMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalPopularPersonList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalRecommendedMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalTopRatedMovieList
import com.murosar.kmp.completemoviesapp.data.mapper.mapToLocalUpcomingMovieList
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
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
    private val movieDetailDao: MovieDetailDao,
    private val movieDetailBelongsToCollectionDao: MovieDetailBelongsToCollectionDao,
    private val movieDetailGenreDao: MovieDetailGenreDao,
    private val movieDetailProductionCompanyDao: MovieDetailProductionCompanyDao,
    private val movieDetailProductionCountryDao: MovieDetailProductionCountryDao,
    private val movieDetailSpokenLanguageDao: MovieDetailSpokenLanguageDao,
    private val movieCollectionDao: MovieCollectionDao,
) : TheMovieDBDatabase {
    override suspend fun insertPopularPersons(popularPersons: List<PopularPerson>) {
        popularPersons.forEach { popularPerson ->
            popularPersonDao.insertPopularPerson(popularPerson.mapToDataBasePopularPerson())

            popularPerson.knownFor.forEach {
                knownForDao.insertKnownFor(it.mapToDataBaseKnownFor(popularPerson.id))
            }
            println("✅ insertKnownFor into DB, SUCCESS")
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
                println("✅ getPopularMovie from DB, SUCCESS")
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
                println("✅ getTopRatedMovie from DB, SUCCESS")
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
                println("✅ getUpcomingMovie from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalUpcomingMovieList())
            } else {
                println("⚠️ getUpcomingMovie from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertRecommendedMovies(
        movieId: Int,
        movies: List<Movie>,
    ) {
        movies.forEach { movie ->
            recommendedMovieDao.insertRecommendedMovie(movie.mapToDataBaseRecommendedMovie(movieId))
        }
        println("✅ insertRecommendedMovies into DB, SUCCESS")
    }

    override suspend fun getRecommendedMoviesById(movieId: Int): CoroutineResult<List<Movie>> =
        recommendedMovieDao.getRecommendedMoviesById(movieId).let {
            if (it.isNotEmpty()) {
                println("✅ getRecommendedMoviesById from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalRecommendedMovieList())
            } else {
                println("⚠️ getRecommendedMoviesById from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertMovieDetail(movieDetail: MovieDetail) {
        movieDetailDao.insertMovieDetail(movieDetail.mapToDataBaseMovieDetail())

        movieDetail.belongsToCollection
            ?.mapToDataBaseMovieDetailBelongsToCollection(movieDetail.id)
            .takeIf { belongsToCollectionEntity -> belongsToCollectionEntity != null }
            ?.let { belongsToCollectionEntity ->
                movieDetailBelongsToCollectionDao.insertMovieDetailBelongsToCollection(belongsToCollectionEntity)
                println("✅ insertMovieDetailBelongsToCollection into DB, SUCCESS")
            }
        movieDetail.genres.forEach {
            movieDetailGenreDao.insertMovieDetailGenre(it.mapToDataBaseMovieDetailGenre(movieDetail.id))
        }
        println("✅ insertMovieDetailGenre into DB, SUCCESS")

        movieDetail.productionCompanies.forEach {
            movieDetailProductionCompanyDao.insertMovieDetailProductionCompany(it.mapToDataBaseMovieDetailProductionCompany(movieDetail.id))
        }
        println("✅ insertMovieDetailProductionCompany into DB, SUCCESS")

        movieDetail.productionCountries.forEach {
            movieDetailProductionCountryDao.insertMovieDetailProductionCountry(it.mapToDataBaseMovieDetailProductionCountry(movieDetail.id))
        }
        println("✅ insertMovieDetailProductionCountry into DB, SUCCESS")

        movieDetail.spokenLanguages.forEach {
            movieDetailSpokenLanguageDao.insertMovieDetailSpokenLanguage(it.mapToDataBaseMovieDetailSpokenLanguage(movieDetail.id))
        }
        println("✅ insertMovieDetailSpokenLanguage into DB, SUCCESS")

        println("✅ insertMovieDetail into DB, SUCCESS")
    }

    override suspend fun getMovieDetailById(movieId: Int): CoroutineResult<MovieDetail> =
        movieDetailDao.getMovieDetailById(movieId).let {
            if (it != null) {
                println("✅ getMovieDetailById from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalMovieDetail())
            } else {
                println("⚠️ getMovieDetailById from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }

    override suspend fun insertMovieCollection(movieCollection: MovieCollection) {
        movieCollectionDao.insertMovieCollection(movieCollection.mapToDataBaseMovieCollection())

        println("✅ insertMovieCollection into DB, SUCCESS")
    }

    override suspend fun getMovieCollectionByName(collectionName: String): CoroutineResult<MovieCollection> =
        movieCollectionDao.getMovieCollectionByName(collectionName).let {
            if (it != null) {
                println("✅ getMovieCollectionByName from DB, SUCCESS")
                CoroutineResult.Success(it.mapToLocalMovieCollection())
            } else {
                println("⚠️ getMovieCollectionByName from DB is empty")
                CoroutineResult.Failure(MovieError.DataBaseError)
            }
        }
}
