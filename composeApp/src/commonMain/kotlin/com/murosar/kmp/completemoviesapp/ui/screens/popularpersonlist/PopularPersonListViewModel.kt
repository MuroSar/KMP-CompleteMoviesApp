package com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.usecase.GetPopularPersonListUseCase
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularPersonListViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val getPopularPersonListUseCase: GetPopularPersonListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<PopularPersonListState>(PopularPersonListState.Idle)
    val uiState: StateFlow<PopularPersonListState> = _uiState

    fun getPopularPeople() =
        viewModelScope.launch {
            withContext(dispatcher) {
                _uiState.update { PopularPersonListState.Loading }
                when (val result = getPopularPersonListUseCase()) {
                    is CoroutineResult.Success -> {
                        _uiState.update { PopularPersonListState.ShowPopularPersonList(result.data) }
                    }

                    is CoroutineResult.Failure -> {
                        _uiState.update { PopularPersonListState.Error(result.error) }
                    }
                }
            }
        }

    sealed class PopularPersonListState {
        data object Idle : PopularPersonListState()

        data object Loading : PopularPersonListState()

        data class ShowPopularPersonList(
            val popularPersonList: List<PopularPerson>,
        ) : PopularPersonListState()

        data class Error(
            val movieError: MovieError,
        ) : PopularPersonListState()
    }
}
