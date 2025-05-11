package com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.murosar.kmp.completemoviesapp.ui.component.CustomTopAppBar
import com.murosar.kmp.completemoviesapp.ui.component.EmptyState
import com.murosar.kmp.completemoviesapp.ui.component.ErrorState
import com.murosar.kmp.completemoviesapp.ui.component.ExpandablePersonCard
import com.murosar.kmp.completemoviesapp.ui.component.LoadingState
import com.murosar.kmp.completemoviesapp.ui.theme.backgroundBrush
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.popular_person_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PopularPersonListScreen(
    viewModel: PopularPersonListViewModel = koinViewModel<PopularPersonListViewModel>(),
    navigateToMovieDetail: (movieId: Int) -> Unit,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getPopularPeople()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(Res.string.popular_person_title),
                onBackClick = { navigateBack() },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        PopularPeopleContent(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            navigateToMovieDetail = { movieId -> navigateToMovieDetail(movieId) },
        )
    }
}

@Composable
fun PopularPeopleContent(
    modifier: Modifier = Modifier,
    uiState: PopularPersonListViewModel.PopularPersonListState,
    navigateToMovieDetail: (movieId: Int) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(padding_16),
    ) {
        when (uiState) {
            PopularPersonListViewModel.PopularPersonListState.Idle -> {}
            PopularPersonListViewModel.PopularPersonListState.Loading -> LoadingState()
            is PopularPersonListViewModel.PopularPersonListState.Error -> ErrorState()
            is PopularPersonListViewModel.PopularPersonListState.ShowPopularPersonList -> {
                if (uiState.popularPersonList.isEmpty()) {
                    EmptyState(isMovieEmptyState = false)
                } else {
                    // Map to store the expansion status of each person
                    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(padding_16),
                        contentPadding = PaddingValues(bottom = padding_16),
                    ) {
                        items(uiState.popularPersonList) { person ->
                            val isExpanded = expandedStates[person.id] ?: false

                            ExpandablePersonCard(
                                person = person,
                                isExpanded = isExpanded,
                                onExpandToggle = { expandedStates[person.id] = !isExpanded },
                                navigateToMovieDetail = { movieId -> navigateToMovieDetail(movieId) },
                            )
                        }
                    }
                }
            }
        }
    }
}
