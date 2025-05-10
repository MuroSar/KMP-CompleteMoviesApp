package com.murosar.kmp.completemoviesapp.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.repository.MovieRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class MovieRepositoryTest {

    private val dataSource = mock(TheMovieDBDataSource::class)
    private val database = mock(TheMovieDBDatabase::class)

    private lateinit var movieRepository: MovieRepository

    @BeforeTest
    fun setup() {
        movieRepository = MovieRepositoryImpl(
            theMovieDBDataSource = dataSource,
            theMovieDBDatabase = database,
        )
    }

    @Test
    fun `should return popularMovies from service`() = runTest {
        val popularMovies = listOf<Movie>()

        coEvery { dataSource.getPopularMovieList() }.returns(CoroutineResult.Success(popularMovies))

        val result = movieRepository.getPopularMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(popularMovies)

        coVerify { dataSource.getPopularMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertPopularMovie(popularMovies) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return popularMovies from DB from repository`() = runTest {
        val popularMovies = listOf<Movie>()

        coEvery { dataSource.getPopularMovieList() }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getPopularMovie() }.returns(CoroutineResult.Success(popularMovies))

        val result = movieRepository.getPopularMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(popularMovies)

        coVerify { dataSource.getPopularMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertPopularMovie(popularMovies) }.wasInvoked(exactly = 0)
        coVerify { database.getPopularMovie() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return topRatedMovies from service`() = runTest {
        val topRatedMovies = listOf<Movie>()

        coEvery { dataSource.getTopRatedMovieList() }.returns(CoroutineResult.Success(topRatedMovies))

        val result = movieRepository.getTopRatedMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(topRatedMovies)

        coVerify { dataSource.getTopRatedMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertTopRatedMovie(topRatedMovies) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return topRatedMovies from DB from repository`() = runTest {
        val topRatedMovies = listOf<Movie>()

        coEvery { dataSource.getTopRatedMovieList() }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getTopRatedMovie() }.returns(CoroutineResult.Success(topRatedMovies))

        val result = movieRepository.getTopRatedMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(topRatedMovies)

        coVerify { dataSource.getTopRatedMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertTopRatedMovie(topRatedMovies) }.wasInvoked(exactly = 0)
        coVerify { database.getTopRatedMovie() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return upcomingMovies from service`() = runTest {
        val upcomingMovies = listOf<Movie>()

        coEvery { dataSource.getUpcomingMovieList() }.returns(CoroutineResult.Success(upcomingMovies))

        val result = movieRepository.getUpcomingMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(upcomingMovies)

        coVerify { dataSource.getUpcomingMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertUpcomingMovie(upcomingMovies) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return upcomingMovies from DB from repository`() = runTest {
        val upcomingMovies = listOf<Movie>()

        coEvery { dataSource.getUpcomingMovieList() }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getUpcomingMovie() }.returns(CoroutineResult.Success(upcomingMovies))

        val result = movieRepository.getUpcomingMovieList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(upcomingMovies)

        coVerify { dataSource.getUpcomingMovieList() }.wasInvoked(exactly = 1)
        coVerify { database.insertUpcomingMovie(upcomingMovies) }.wasInvoked(exactly = 0)
        coVerify { database.getUpcomingMovie() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return recommendedMovies from service`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { dataSource.getRecommendedMoviesListById(MOVIE_ID) }.returns(CoroutineResult.Success(recommendedMovies))

        val result = movieRepository.getRecommendedMoviesListById(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(recommendedMovies)

        coVerify { dataSource.getRecommendedMoviesListById(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { database.insertRecommendedMovies(MOVIE_ID, recommendedMovies) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return recommendedMovies from DB from repository`() = runTest {
        val recommendedMovies = listOf<Movie>()

        coEvery { dataSource.getRecommendedMoviesListById(MOVIE_ID) }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getRecommendedMoviesById(MOVIE_ID) }.returns(CoroutineResult.Success(recommendedMovies))

        val result = movieRepository.getRecommendedMoviesListById(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<Movie>>>()
        assertThat((result as CoroutineResult.Success<List<Movie>>).data).isEqualTo(recommendedMovies)

        coVerify { dataSource.getRecommendedMoviesListById(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { database.insertRecommendedMovies(MOVIE_ID, recommendedMovies) }.wasInvoked(exactly = 0)
        coVerify { database.getRecommendedMoviesById(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return movieDetail from service`() = runTest {
        coEvery { dataSource.getMovieDetailById(MOVIE_ID) }.returns(CoroutineResult.Success(movieDetail))

        val result = movieRepository.getMovieDetailById(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Success<MovieDetail>>()
        assertThat((result as CoroutineResult.Success<MovieDetail>).data).isEqualTo(movieDetail)

        coVerify { dataSource.getMovieDetailById(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { database.insertMovieDetail(movieDetail) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return movieDetail from DB from repository`() = runTest {
        coEvery { dataSource.getMovieDetailById(MOVIE_ID) }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getMovieDetailById(MOVIE_ID) }.returns(CoroutineResult.Success(movieDetail))

        val result = movieRepository.getMovieDetailById(MOVIE_ID)

        assertThat(result).isInstanceOf<CoroutineResult.Success<MovieDetail>>()
        assertThat((result as CoroutineResult.Success<MovieDetail>).data).isEqualTo(movieDetail)

        coVerify { dataSource.getMovieDetailById(MOVIE_ID) }.wasInvoked(exactly = 1)
        coVerify { database.insertMovieDetail(movieDetail) }.wasInvoked(exactly = 0)
        coVerify { database.getMovieDetailById(MOVIE_ID) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return movieCollection from service`() = runTest {
        coEvery { dataSource.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.returns(CoroutineResult.Success(movieCollection))

        val result = movieRepository.getMovieCollectionByName(MOVIE_COLLECTION_NAME)

        assertThat(result).isInstanceOf<CoroutineResult.Success<MovieCollection>>()
        assertThat((result as CoroutineResult.Success<MovieCollection>).data).isEqualTo(movieCollection)

        coVerify { dataSource.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.wasInvoked(exactly = 1)
        coVerify { database.insertMovieCollection(movieCollection) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return movieCollection from DB from repository`() = runTest {
        coEvery { dataSource.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.returns(CoroutineResult.Success(movieCollection))

        val result = movieRepository.getMovieCollectionByName(MOVIE_COLLECTION_NAME)

        assertThat(result).isInstanceOf<CoroutineResult.Success<MovieCollection>>()
        assertThat((result as CoroutineResult.Success<MovieCollection>).data).isEqualTo(movieCollection)

        coVerify { dataSource.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.wasInvoked(exactly = 1)
        coVerify { database.insertMovieCollection(movieCollection) }.wasInvoked(exactly = 0)
        coVerify { database.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.wasInvoked(exactly = 1)
    }

    companion object {
        private const val MOVIE_ID = 9292
        private const val MOVIE_COLLECTION_NAME = "The Godfather"

        private val movieDetail = MovieDetail(
            id = 6761,
            adult = false,
            backdropPath = "consul",
            belongsToCollection = null,
            budget = 3874,
            genres = listOf(),
            homepage = "alia",
            imdbId = "dolores",
            originCountry = "Vanuatu",
            originalLanguage = "vim",
            originalTitle = "saepe",
            overview = "dictas",
            popularity = 12.13,
            posterPath = "mediocritatem",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            releaseDate = "vocibus",
            revenue = 8223,
            runtime = 7233,
            spokenLanguages = listOf(),
            status = "prompta",
            tagline = "nascetur",
            title = "himenaeos",
            video = false,
            voteAverage = 14.15,
            voteCount = 9150
        )

        private val movieCollection = MovieCollection(
            id = 6469,
            adult = false,
            backdropPath = "omittam",
            name = "The Godfather",
            originalLanguage = "voluptatum",
            originalName = "Saundra McFarland",
            overview = "massa",
            posterPath = "option"

        )
    }

}
