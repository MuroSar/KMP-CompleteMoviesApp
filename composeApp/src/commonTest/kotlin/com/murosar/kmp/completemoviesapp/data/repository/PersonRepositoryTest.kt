package com.murosar.kmp.completemoviesapp.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.database.TheMovieDBDatabase
import com.murosar.kmp.completemoviesapp.domain.datasource.TheMovieDBDataSource
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.test.runTest

class PersonRepositoryTest {

    private val dataSource = mock(TheMovieDBDataSource::class)
    private val database = mock(TheMovieDBDatabase::class)

    private lateinit var personRepository: PersonRepository

    @BeforeTest
    fun setup() {
        personRepository = PersonRepositoryImpl(
            theMovieDBDataSource = dataSource,
            theMovieDBDatabase = database,
        )
    }

    @Test
    fun `should return popularPersonList from service`() = runTest {
        val popularPersonList = listOf<PopularPerson>()

        coEvery { dataSource.getPopularPersonList() }.returns(CoroutineResult.Success(popularPersonList))

        val result = personRepository.getPopularPersonList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<PopularPerson>>>()
        assertThat((result as CoroutineResult.Success<List<PopularPerson>>).data).isEqualTo(popularPersonList)

        coVerify { dataSource.getPopularPersonList() }.wasInvoked(exactly = 1)
        coVerify { database.insertPopularPersons(popularPersonList) }.wasInvoked(exactly = 1)
    }

    @Test
    fun `should return popularPersonList from DB from repository`() = runTest {
        val popularPersonList = listOf<PopularPerson>()

        coEvery { dataSource.getPopularPersonList() }.returns(CoroutineResult.Failure(MovieError.NoInternet))
        coEvery { database.getPopularPersons() }.returns(CoroutineResult.Success(popularPersonList))

        val result = personRepository.getPopularPersonList()

        assertThat(result).isInstanceOf<CoroutineResult.Success<List<PopularPerson>>>()
        assertThat((result as CoroutineResult.Success<List<PopularPerson>>).data).isEqualTo(popularPersonList)

        coVerify { dataSource.getPopularPersonList() }.wasInvoked(exactly = 1)
        coVerify { database.insertPopularPersons(popularPersonList) }.wasInvoked(exactly = 0)
        coVerify { database.getPopularPersons() }.wasInvoked(exactly = 1)
    }
}
