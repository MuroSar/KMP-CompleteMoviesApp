package com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularPersonListUseCase
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class PopularPersonListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val getPopularPersonListUseCase = mock(GetPopularPersonListUseCase::class)

    private lateinit var viewModel: PopularPersonListViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PopularPersonListViewModel(
            dispatcher = testDispatcher,
            getPopularPersonListUseCase = getPopularPersonListUseCase
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPopularPeople should return Success`() = runTest {
        val popularPeople = listOf<PopularPerson>()
        coEvery { getPopularPersonListUseCase() } returns CoroutineResult.Success(popularPeople)

        viewModel.uiState.test {
            viewModel.getPopularPeople()

            assertThat(awaitItem()).isInstanceOf<PopularPersonListViewModel.PopularPersonListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<PopularPersonListViewModel.PopularPersonListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                PopularPersonListViewModel.PopularPersonListState.ShowPopularPersonList(popularPeople)
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularPersonListUseCase() }.wasInvoked(exactly = 1)
    }

    @Test
    fun `getPopularPeople should return Failure`() = runTest {
        coEvery { getPopularPersonListUseCase() } returns CoroutineResult.Failure(MovieError.NoInternet)

        viewModel.uiState.test {
            viewModel.getPopularPeople()

            assertThat(awaitItem()).isInstanceOf<PopularPersonListViewModel.PopularPersonListState.Idle>()
            assertThat(awaitItem()).isInstanceOf<PopularPersonListViewModel.PopularPersonListState.Loading>()
            assertThat(awaitItem()).isEqualTo(
                PopularPersonListViewModel.PopularPersonListState.Error(MovieError.NoInternet)
            )

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { getPopularPersonListUseCase() }.wasInvoked(exactly = 1)
    }
}
