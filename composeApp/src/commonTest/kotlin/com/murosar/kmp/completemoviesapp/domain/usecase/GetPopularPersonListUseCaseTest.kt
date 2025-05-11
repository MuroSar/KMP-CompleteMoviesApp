package com.murosar.kmp.completemoviesapp.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetPopularPersonListUseCaseTest {
    private val repository = mock(PersonRepository::class)

    private lateinit var getPopularPersonListUseCase: GetPopularPersonListUseCase

    @BeforeTest
    fun setup() {
        getPopularPersonListUseCase = GetPopularPersonListUseCaseImpl(repository)
    }

    @Test
    fun `should return popularPersonList from repository`() =
        runTest {
            val popularPerson = listOf<PopularPerson>()

            coEvery { repository.getPopularPersonList() }.returns(CoroutineResult.Success(popularPerson))

            val result = getPopularPersonListUseCase()

            assertThat(result).isInstanceOf<CoroutineResult.Success<List<PopularPerson>>>()
            assertThat((result as CoroutineResult.Success<List<PopularPerson>>).data).isEqualTo(popularPerson)

            coVerify { repository.getPopularPersonList() }.wasInvoked(exactly = 1)
        }

    @Test
    fun `should return SerializationError from repository`() =
        runTest {
            coEvery { repository.getPopularPersonList() }.returns(CoroutineResult.Failure(MovieError.SerializationError))

            val result = getPopularPersonListUseCase()

            assertThat(result).isInstanceOf<CoroutineResult.Failure>()
            assertThat((result as CoroutineResult.Failure).error).isEqualTo(MovieError.SerializationError)

            coVerify { repository.getPopularPersonList() }.wasInvoked(exactly = 1)
        }
}
