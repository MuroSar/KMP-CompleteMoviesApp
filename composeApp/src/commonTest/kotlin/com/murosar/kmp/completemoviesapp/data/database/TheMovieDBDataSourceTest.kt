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
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntityWithKnownForEntity
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.model.KnownFor
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
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

    companion object {
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
