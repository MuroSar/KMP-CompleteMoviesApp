package com.murosar.kmp.completemoviesapp.data.database

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
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
import com.murosar.kmp.completemoviesapp.data.database.entity.KnownForEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailBelongsToCollectionEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailGenreEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCompanyEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailProductionCountryEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailSpokenLanguageEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieDetailWithRelations
import com.murosar.kmp.completemoviesapp.data.database.entity.MovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntityWithKnownForEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.RecommendedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.TopRatedMovieEntityComplete
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.UpcomingMovieEntityComplete
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.Genre
import com.murosar.kmp.completemoviesapp.domain.model.KnownFor
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCompany
import com.murosar.kmp.completemoviesapp.domain.model.ProductionCountry
import com.murosar.kmp.completemoviesapp.domain.model.SpokenLanguage
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.any
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TheMovieDBDataSourceTest {
    private val popularPersonDao = mock(PopularPersonDao::class)
    private val knownForDao = mock(KnownForDao::class)
    private val popularMovieDao = mock(PopularMovieDao::class)
    private val topRatedMovieDao = mock(TopRatedMovieDao::class)
    private val upcomingMovieDao = mock(UpcomingMovieDao::class)
    private val recommendedMovieDao = mock(RecommendedMovieDao::class)
    private val movieDetailDao = mock(MovieDetailDao::class)
    private val movieDetailBelongsToCollectionDao = mock(MovieDetailBelongsToCollectionDao::class)
    private val movieDetailGenreDao = mock(MovieDetailGenreDao::class)
    private val movieDetailProductionCompanyDao = mock(MovieDetailProductionCompanyDao::class)
    private val movieDetailProductionCountryDao = mock(MovieDetailProductionCountryDao::class)
    private val movieDetailSpokenLanguageDao = mock(MovieDetailSpokenLanguageDao::class)
    private val movieCollectionDao = mock(MovieCollectionDao::class)

    private lateinit var database: TheMovieDBDatabase

    @BeforeTest
    fun setup() {
        database =
            TheMovieDBDatabaseImpl(
                popularPersonDao = popularPersonDao,
                knownForDao = knownForDao,
                popularMovieDao = popularMovieDao,
                topRatedMovieDao = topRatedMovieDao,
                upcomingMovieDao = upcomingMovieDao,
                recommendedMovieDao = recommendedMovieDao,
                movieDetailDao = movieDetailDao,
                movieDetailBelongsToCollectionDao = movieDetailBelongsToCollectionDao,
                movieDetailGenreDao = movieDetailGenreDao,
                movieDetailProductionCompanyDao = movieDetailProductionCompanyDao,
                movieDetailProductionCountryDao = movieDetailProductionCountryDao,
                movieDetailSpokenLanguageDao = movieDetailSpokenLanguageDao,
                movieCollectionDao = movieCollectionDao,
            )
    }

    @Test
    fun `insertPopularPersons should insert persons and knownFor`() =
        runTest {
            coEvery { popularPersonDao.insertPopularPerson(any<PopularPersonEntity>()) }.returns(Unit)
            coEvery { knownForDao.insertKnownFor(any<KnownForEntity>()) }.returns(Unit)

            database.insertPopularPersons(listOf(popularPerson))

            coVerify { popularPersonDao.insertPopularPerson(any<PopularPersonEntity>()) }.wasInvoked(exactly = 1)
            coVerify { knownForDao.insertKnownFor(any<KnownForEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `insertPopularPersons should insert persons`() =
        runTest {
            coEvery { popularPersonDao.insertPopularPerson(any<PopularPersonEntity>()) }.returns(Unit)

            database.insertPopularPersons(listOf(popularPersonWithoutKnownFor))

            coVerify { popularPersonDao.insertPopularPerson(any<PopularPersonEntity>()) }.wasInvoked(exactly = 1)
            coVerify { knownForDao.insertKnownFor(any<KnownForEntity>()) }.wasInvoked(exactly = 0)
        }

    @Test
    fun `getPopularPersons should return success when dao returns data`() =
        runTest {
            coEvery { popularPersonDao.getPopularPersons() }.returns(listOf(popularPersonEntityWithKnownForEntity))

            val result = database.getPopularPersons()

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).hasSize(1)
            assertThat(result.data.first()).isEqualTo(popularPerson)
        }

    @Test
    fun `getPopularPersons returns Failure when empty`() =
        runTest {
            coEvery { popularPersonDao.getPopularPersons() } returns emptyList()

            val result = database.getPopularPersons()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertPopularMovie should insert movies`() =
        runTest {
            coEvery { popularMovieDao.insertPopularMovie(any<PopularMovieEntity>()) }.returns(Unit)

            database.insertPopularMovie(listOf(movie))

            coVerify { popularMovieDao.insertPopularMovie(any<PopularMovieEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `getPopularMovie should return success when dao returns data`() =
        runTest {
            coEvery { popularMovieDao.getPopularMovies() }.returns(listOf(popularMovieEntityComplete))

            val result = database.getPopularMovie()

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).hasSize(1)
            assertThat(result.data.first()).isEqualTo(movie)
        }

    @Test
    fun `getPopularMovie returns Failure when empty`() =
        runTest {
            coEvery { popularMovieDao.getPopularMovies() } returns emptyList()

            val result = database.getPopularMovie()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertTopRatedMovie should insert movies`() =
        runTest {
            coEvery { topRatedMovieDao.insertTopRatedMovie(any<TopRatedMovieEntity>()) }.returns(Unit)

            database.insertTopRatedMovie(listOf(movie))

            coVerify { topRatedMovieDao.insertTopRatedMovie(any<TopRatedMovieEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `getTopRatedMovie should return success when dao returns data`() =
        runTest {
            coEvery { topRatedMovieDao.getTopRatedMovies() }.returns(listOf(topRatedMovieEntityComplete))

            val result = database.getTopRatedMovie()

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).hasSize(1)
            assertThat(result.data.first()).isEqualTo(movie)
        }

    @Test
    fun `getTopRatedMovie returns Failure when empty`() =
        runTest {
            coEvery { topRatedMovieDao.getTopRatedMovies() } returns emptyList()

            val result = database.getTopRatedMovie()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertUpcomingMovie should insert movies`() =
        runTest {
            coEvery { upcomingMovieDao.insertUpcomingMovie(any<UpcomingMovieEntity>()) }.returns(Unit)

            database.insertUpcomingMovie(listOf(movie))

            coVerify { upcomingMovieDao.insertUpcomingMovie(any<UpcomingMovieEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `getUpcomingMovie should return success when dao returns data`() =
        runTest {
            coEvery { upcomingMovieDao.getUpcomingMovies() }.returns(listOf(upcomingMovieEntityComplete))

            val result = database.getUpcomingMovie()

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).hasSize(1)
            assertThat(result.data.first()).isEqualTo(movie)
        }

    @Test
    fun `getUpcomingMovie returns Failure when empty`() =
        runTest {
            coEvery { upcomingMovieDao.getUpcomingMovies() } returns emptyList()

            val result = database.getUpcomingMovie()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertRecommendedMovies should insert movies`() =
        runTest {
            coEvery { recommendedMovieDao.insertRecommendedMovie(any<RecommendedMovieEntity>()) }.returns(Unit)

            database.insertRecommendedMovies(MOVIE_ID, listOf(movie))

            coVerify { recommendedMovieDao.insertRecommendedMovie(any<RecommendedMovieEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `getRecommendedMoviesById should return success when dao returns data`() =
        runTest {
            coEvery { recommendedMovieDao.getRecommendedMoviesById(MOVIE_ID) }.returns(listOf(recommendedMovieEntityComplete))

            val result = database.getRecommendedMoviesById(MOVIE_ID)

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).hasSize(1)
            assertThat(result.data.first()).isEqualTo(movie)
        }

    @Test
    fun `getRecommendedMoviesById returns Failure when empty`() =
        runTest {
            coEvery { recommendedMovieDao.getRecommendedMoviesById(MOVIE_ID) } returns emptyList()

            val result = database.getRecommendedMoviesById(MOVIE_ID)

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertMovieDetail should insert persons and knownFor`() =
        runTest {
            coEvery { movieDetailDao.insertMovieDetail(any<MovieDetailEntity>()) }.returns(Unit)
            coEvery { movieDetailGenreDao.insertMovieDetailGenre(any<MovieDetailGenreEntity>()) }.returns(Unit)
            coEvery {
                movieDetailProductionCompanyDao.insertMovieDetailProductionCompany(any<MovieDetailProductionCompanyEntity>())
            }.returns(Unit)
            coEvery {
                movieDetailProductionCountryDao.insertMovieDetailProductionCountry(any<MovieDetailProductionCountryEntity>())
            }.returns(Unit)
            coEvery { movieDetailSpokenLanguageDao.insertMovieDetailSpokenLanguage(any<MovieDetailSpokenLanguageEntity>()) }.returns(Unit)

            database.insertMovieDetail(movieDetail)

            coVerify { movieDetailDao.insertMovieDetail(any<MovieDetailEntity>()) }.wasInvoked(exactly = 1)
            coVerify { movieDetailGenreDao.insertMovieDetailGenre(any<MovieDetailGenreEntity>()) }.wasInvoked(exactly = 1)
            coVerify { movieDetailProductionCompanyDao.insertMovieDetailProductionCompany(any<MovieDetailProductionCompanyEntity>()) }
                .wasInvoked(exactly = 1)
            coVerify { movieDetailProductionCountryDao.insertMovieDetailProductionCountry(any<MovieDetailProductionCountryEntity>()) }
                .wasInvoked(exactly = 1)
            coVerify { movieDetailSpokenLanguageDao.insertMovieDetailSpokenLanguage(any<MovieDetailSpokenLanguageEntity>()) }
                .wasInvoked(exactly = 1)
        }

    @Test
    fun `insertMovieDetail should insert persons`() =
        runTest {
            coEvery { movieDetailDao.insertMovieDetail(any<MovieDetailEntity>()) }.returns(Unit)

            database.insertMovieDetail(movieDetailUncompleted)

            coVerify { movieDetailDao.insertMovieDetail(any<MovieDetailEntity>()) }.wasInvoked(exactly = 1)
            coVerify { movieDetailGenreDao.insertMovieDetailGenre(any<MovieDetailGenreEntity>()) }.wasInvoked(exactly = 0)
            coVerify { movieDetailProductionCompanyDao.insertMovieDetailProductionCompany(any<MovieDetailProductionCompanyEntity>()) }
                .wasInvoked(exactly = 0)
            coVerify { movieDetailProductionCountryDao.insertMovieDetailProductionCountry(any<MovieDetailProductionCountryEntity>()) }
                .wasInvoked(exactly = 0)
            coVerify { movieDetailSpokenLanguageDao.insertMovieDetailSpokenLanguage(any<MovieDetailSpokenLanguageEntity>()) }
                .wasInvoked(exactly = 0)
        }

    @Test
    fun `getMovieDetailById should return success when dao returns data`() =
        runTest {
            coEvery { movieDetailDao.getMovieDetailById(MOVIE_ID) }.returns(movieDetailWithRelations)

            val result = database.getMovieDetailById(MOVIE_ID)

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).isEqualTo(movieDetail)
        }

    @Test
    fun `getMovieDetailById returns Failure when empty`() =
        runTest {
            coEvery { movieDetailDao.getMovieDetailById(MOVIE_ID) } returns null

            val result = database.getMovieDetailById(MOVIE_ID)

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    @Test
    fun `insertMovieCollection should insert movies`() =
        runTest {
            coEvery { movieCollectionDao.insertMovieCollection(any<MovieCollectionEntity>()) }.returns(Unit)

            database.insertMovieCollection(movieCollection)

            coVerify { movieCollectionDao.insertMovieCollection(any<MovieCollectionEntity>()) }.wasInvoked(exactly = 1)
        }

    @Test
    fun `getMovieCollectionByName should return success when dao returns data`() =
        runTest {
            coEvery { movieCollectionDao.getMovieCollectionByName(MOVIE_COLLECTION_NAME) }.returns(movieCollectionEntity)

            val result = database.getMovieCollectionByName(MOVIE_COLLECTION_NAME)

            assertThat(result).isInstanceOf(CoroutineResult.Success::class)
            result as CoroutineResult.Success
            assertThat(result.data).isEqualTo(movieCollection)
        }

    @Test
    fun `getMovieCollectionByName returns Failure when empty`() =
        runTest {
            coEvery { movieCollectionDao.getMovieCollectionByName(MOVIE_COLLECTION_NAME) } returns null

            val result = database.getMovieCollectionByName(MOVIE_COLLECTION_NAME)

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.DataBaseError)
        }

    companion object {
        private const val MOVIE_ID = 2953
        private const val MOVIE_COLLECTION_NAME = "The Godfather"
        private val movieCollectionEntity =
            MovieCollectionEntity(
                id = 9457,
                adult = false,
                backdropPath = "quo",
                name = MOVIE_COLLECTION_NAME,
                originalLanguage = "urna",
                originalName = "Kenneth Gallagher",
                overview = "aperiri",
                posterPath = "verear",
            )
        private val movieCollection =
            MovieCollection(
                id = 9457,
                adult = false,
                backdropPath = "quo",
                name = MOVIE_COLLECTION_NAME,
                originalLanguage = "urna",
                originalName = "Kenneth Gallagher",
                overview = "aperiri",
                posterPath = "verear",
            )
        private val movieDetailUncompleted =
            MovieDetail(
                id = MOVIE_ID,
                adult = false,
                backdropPath = "magna",
                belongsToCollection = null,
                budget = 9660,
                genres = listOf(),
                homepage = "justo",
                imdbId = "sem",
                originCountry = "Malaysia",
                originalLanguage = "faucibus",
                originalTitle = "ultricies",
                overview = "pulvinar",
                popularity = 92.93,
                posterPath = "eripuit",
                productionCompanies = listOf(),
                productionCountries = listOf(),
                releaseDate = "laoreet",
                revenue = 9831,
                runtime = 6910,
                spokenLanguages = listOf(),
                status = "discere",
                tagline = "omittam",
                title = "nostrum",
                video = false,
                voteAverage = 94.95,
                voteCount = 5319,
            )
        private val movieDetail =
            MovieDetail(
                id = MOVIE_ID,
                adult = false,
                backdropPath = "magna",
                belongsToCollection =
                    BelongsToCollection(
                        id = 7586,
                        name = "Brady Woodward",
                        posterPath = "verterem",
                        backdropPath = "nonumy",
                    ),
                budget = 9660,
                genres = listOf(Genre(id = 7872, name = "Sheldon Vega")),
                homepage = "justo",
                imdbId = "sem",
                originCountry = "Malaysia",
                originalLanguage = "faucibus",
                originalTitle = "ultricies",
                overview = "pulvinar",
                popularity = 92.93,
                posterPath = "eripuit",
                productionCompanies =
                    listOf(
                        ProductionCompany(
                            id = 8041,
                            logoPath = "elaboraret",
                            name = "Melody Allen",
                            originCountry = "Taiwan",
                        ),
                    ),
                productionCountries = listOf(ProductionCountry(iso_3166_1 = "dis", name = "Emma Davidson")),
                releaseDate = "laoreet",
                revenue = 9831,
                runtime = 6910,
                spokenLanguages =
                    listOf(
                        SpokenLanguage(englishName = "Charles Strickland", iso_639_1 = "sociosqu", name = "Whitney Harris"),
                    ),
                status = "discere",
                tagline = "omittam",
                title = "nostrum",
                video = false,
                voteAverage = 94.95,
                voteCount = 5319,
            )
        private val movieDetailWithRelations =
            MovieDetailWithRelations(
                movieDetail =
                    MovieDetailEntity(
                        id = MOVIE_ID,
                        adult = false,
                        backdropPath = "magna",
                        budget = 9660,
                        homepage = "justo",
                        imdbId = "sem",
                        originCountry = "Malaysia",
                        originalLanguage = "faucibus",
                        originalTitle = "ultricies",
                        overview = "pulvinar",
                        popularity = 92.93,
                        posterPath = "eripuit",
                        releaseDate = "laoreet",
                        revenue = 9831,
                        runtime = 6910,
                        status = "discere",
                        tagline = "omittam",
                        title = "nostrum",
                        video = false,
                        voteAverage = 94.95,
                        voteCount = 5319,
                    ),
                belongsToCollection =
                    MovieDetailBelongsToCollectionEntity(
                        id = 7586,
                        movieId = 8830,
                        name = "Brady Woodward",
                        posterPath = "verterem",
                        backdropPath = "nonumy",
                    ),
                genres = listOf(MovieDetailGenreEntity(id = 7872, movieId = 8830, name = "Sheldon Vega")),
                productionCompanies =
                    listOf(
                        MovieDetailProductionCompanyEntity(
                            id = 8041,
                            movieId = 8830,
                            logoPath = "elaboraret",
                            name = "Melody Allen",
                            originCountry = "Taiwan",
                        ),
                    ),
                productionCountries =
                    listOf(
                        MovieDetailProductionCountryEntity(isoCode = "dis", movieId = 8830, name = "Emma Davidson"),
                    ),
                spokenLanguages =
                    listOf(
                        MovieDetailSpokenLanguageEntity(
                            englishName = "Charles Strickland",
                            movieId = 8830,
                            isoCode = "sociosqu",
                            name = "Whitney Harris",
                        ),
                    ),
            )
        private val recommendedMovieEntityComplete =
            RecommendedMovieEntityComplete(
                movieEntity =
                    MovieEntity(
                        id = 2404,
                        adult = false,
                        backdropPath = "noster",
                        originalLanguage = "theophrastus",
                        originalTitle = "honestatis",
                        overview = "dapibus",
                        popularity = 20.21,
                        posterPath = "utinam",
                        releaseDate = "mei",
                        title = "legimus",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 2884,
                    ),
                recommendedMovieEntity =
                    RecommendedMovieEntity(
                        recommendedMovieId = MOVIE_ID,
                        originalMovieId = 9396,
                        movieEntity =
                            MovieEntity(
                                id = 1748,
                                adult = false,
                                backdropPath = "omnesque",
                                originalLanguage = "diam",
                                originalTitle = "sit",
                                overview = "tation",
                                popularity = 84.85,
                                posterPath = "evertitur",
                                releaseDate = "adipisci",
                                title = "litora",
                                video = false,
                                voteAverage = 86.87,
                                voteCount = 2129,
                            ),
                    ),
            )
        private val upcomingMovieEntityComplete =
            UpcomingMovieEntityComplete(
                movieEntity =
                    MovieEntity(
                        id = 5256,
                        adult = false,
                        backdropPath = "noster",
                        originalLanguage = "theophrastus",
                        originalTitle = "honestatis",
                        overview = "dapibus",
                        popularity = 20.21,
                        posterPath = "utinam",
                        releaseDate = "mei",
                        title = "legimus",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 2884,
                    ),
                upcomingMovieEntity =
                    UpcomingMovieEntity(
                        upcomingMovieId = MOVIE_ID,
                        movieEntity =
                            MovieEntity(
                                id = 1581,
                                adult = false,
                                backdropPath = "salutatus",
                                originalLanguage = "saperet",
                                originalTitle = "congue",
                                overview = "iaculis",
                                popularity = 68.69,
                                posterPath = "aliquid",
                                releaseDate = "impetus",
                                title = "persequeris",
                                video = false,
                                voteAverage = 70.71,
                                voteCount = 4611,
                            ),
                    ),
            )
        private val topRatedMovieEntityComplete =
            TopRatedMovieEntityComplete(
                movieEntity =
                    MovieEntity(
                        id = 6178,
                        adult = false,
                        backdropPath = "noster",
                        originalLanguage = "theophrastus",
                        originalTitle = "honestatis",
                        overview = "dapibus",
                        popularity = 20.21,
                        posterPath = "utinam",
                        releaseDate = "mei",
                        title = "legimus",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 2884,
                    ),
                topRatedMovieEntity =
                    TopRatedMovieEntity(
                        topRatedMovieId = MOVIE_ID,
                        movieEntity =
                            MovieEntity(
                                id = 9120,
                                adult = false,
                                backdropPath = "nobis",
                                originalLanguage = "mattis",
                                originalTitle = "atqui",
                                overview = "numquam",
                                popularity = 52.53,
                                posterPath = "nobis",
                                releaseDate = "urna",
                                title = "vulputate",
                                video = false,
                                voteAverage = 54.55,
                                voteCount = 9454,
                            ),
                    ),
            )
        private val popularMovieEntityComplete =
            PopularMovieEntityComplete(
                movieEntity =
                    MovieEntity(
                        id = 2978,
                        adult = false,
                        backdropPath = "noster",
                        originalLanguage = "theophrastus",
                        originalTitle = "honestatis",
                        overview = "dapibus",
                        popularity = 20.21,
                        posterPath = "utinam",
                        releaseDate = "mei",
                        title = "legimus",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 2884,
                    ),
                popularMovieEntity =
                    PopularMovieEntity(
                        popularMovieId = MOVIE_ID,
                        movieEntity =
                            MovieEntity(
                                id = 8781,
                                adult = false,
                                backdropPath = "delicata",
                                originalLanguage = "nostra",
                                originalTitle = "ornare",
                                overview = "invidunt",
                                popularity = 36.37,
                                posterPath = "legimus",
                                releaseDate = "eam",
                                title = "eam",
                                video = false,
                                voteAverage = 38.39,
                                voteCount = 2835,
                            ),
                    ),
            )
        private val movie =
            Movie(
                id = MOVIE_ID,
                adult = false,
                backdropPath = "noster",
                originalLanguage = "theophrastus",
                originalTitle = "honestatis",
                overview = "dapibus",
                popularity = 20.21,
                posterPath = "utinam",
                releaseDate = "mei",
                title = "legimus",
                video = false,
                voteAverage = 22.23,
                voteCount = 2884,
            )
        private val knownFor =
            KnownFor(
                id = 3304,
                adult = false,
                backdropPath = "consequat",
                firstAirDate = "dui",
                mediaType = "consectetur",
                name = "Carrie Griffin",
                originalLanguage = "volutpat",
                originalName = "Katheryn Foley",
                originalTitle = "curabitur",
                overview = "lacus",
                popularity = 8.9,
                posterPath = "quidam",
                releaseDate = "ponderum",
                title = "voluptaria",
                video = false,
                voteAverage = 10.11,
                voteCount = 1306,
            )
        private val popularPerson =
            PopularPerson(
                id = 9360,
                adult = false,
                gender = 9905,
                knownFor = listOf(knownFor),
                knownForDepartment = "consequat",
                name = "Houston Kelly",
                popularity = 2.3,
                profilePath = "cum",
            )
        private val popularPersonWithoutKnownFor =
            PopularPerson(
                id = 9360,
                adult = false,
                gender = 9905,
                knownFor = listOf(),
                knownForDepartment = "consequat",
                name = "Houston Kelly",
                popularity = 2.3,
                profilePath = "cum",
            )
        private val knownForEntity =
            KnownForEntity(
                id = 3304,
                userId = 9360,
                adult = false,
                backdropPath = "consequat",
                firstAirDate = "dui",
                mediaType = "consectetur",
                name = "Carrie Griffin",
                originalLanguage = "volutpat",
                originalName = "Katheryn Foley",
                originalTitle = "curabitur",
                overview = "lacus",
                popularity = 8.9,
                posterPath = "quidam",
                releaseDate = "ponderum",
                title = "voluptaria",
                video = false,
                voteAverage = 10.11,
                voteCount = 1306,
            )
        private val popularPersonEntityWithKnownForEntity =
            PopularPersonEntityWithKnownForEntity(
                popularPersonEntity =
                    PopularPersonEntity(
                        id = 9360,
                        adult = false,
                        gender = 9905,
                        knownForDepartment = "consequat",
                        name = "Houston Kelly",
                        popularity = 2.3,
                        profilePath = "cum",
                    ),
                knownForEntity = listOf(knownForEntity),
            )
    }
}
