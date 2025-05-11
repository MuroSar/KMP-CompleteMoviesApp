package com.murosar.kmp.completemoviesapp.ui.screens.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.ui.component.CustomTopAppBar
import com.murosar.kmp.completemoviesapp.ui.component.EmptyState
import com.murosar.kmp.completemoviesapp.ui.component.ErrorState
import com.murosar.kmp.completemoviesapp.ui.component.LoadingState
import com.murosar.kmp.completemoviesapp.ui.component.MostPopularMovieCard
import com.murosar.kmp.completemoviesapp.ui.component.TopRatedMovieCard
import com.murosar.kmp.completemoviesapp.ui.component.UpcomingMovieCard
import com.murosar.kmp.completemoviesapp.ui.screens.movielist.MovieListViewModel.MovieListState
import com.murosar.kmp.completemoviesapp.ui.theme.backgroundBrush
import com.murosar.kmp.completemoviesapp.ui.theme.movieListSectionTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_32
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.movie_list_section_title_most_popular
import completemoviesapp.composeapp.generated.resources.movie_list_section_title_top_rated
import completemoviesapp.composeapp.generated.resources.movie_list_section_title_upcoming
import completemoviesapp.composeapp.generated.resources.movie_list_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>(),
    navigateToMovieDetail: (movie: Movie) -> Unit,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(Res.string.movie_list_title),
                onBackClick = { navigateBack() },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        MovieListContent(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            navigateToMovieDetail = { movie -> navigateToMovieDetail(movie) },
        )
    }
}

@Composable
fun MovieListContent(
    modifier: Modifier = Modifier,
    uiState: MovieListState,
    navigateToMovieDetail: (movie: Movie) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(backgroundBrush)
                .padding(padding_16)
                .verticalScroll(rememberScrollState()),
    ) {
        // Upcoming Movies
        MovieListSection(
            modifier = Modifier.padding(top = padding_16),
            title = stringResource(Res.string.movie_list_section_title_upcoming),
            uiState = uiState,
            content = { showMovieListsState ->
                if (showMovieListsState.upcomingMovieList.isEmpty()) {
                    EmptyState()
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(padding_16),
                        contentPadding = PaddingValues(horizontal = padding_8),
                    ) {
                        items(showMovieListsState.upcomingMovieList.size) { index ->
                            UpcomingMovieCard(
                                movieTitle = showMovieListsState.upcomingMovieList[index].title,
                                posterUrl = showMovieListsState.upcomingMovieList[index].posterPath,
                                onClick = {
                                    navigateToMovieDetail(showMovieListsState.upcomingMovieList[index])
                                },
                            )
                        }
                    }
                }
            },
        )

        // Top Rated Movies
        MovieListSection(
            modifier = Modifier.padding(top = padding_32),
            title = stringResource(Res.string.movie_list_section_title_top_rated),
            uiState = uiState,
            content = { showMovieListsState ->
                if (showMovieListsState.topRatedMovieList.isEmpty()) {
                    EmptyState()
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(padding_16),
                        contentPadding = PaddingValues(horizontal = padding_8),
                    ) {
                        items(showMovieListsState.topRatedMovieList.size) { index ->
                            TopRatedMovieCard(
                                movieTitle = showMovieListsState.topRatedMovieList[index].title,
                                posterUrl = showMovieListsState.topRatedMovieList[index].posterPath,
                                onClick = {
                                    navigateToMovieDetail(showMovieListsState.topRatedMovieList[index])
                                },
                            )
                        }
                    }
                }
            },
        )

        // Most Popular Movies
        MovieListSection(
            modifier = Modifier.padding(top = padding_32),
            title = stringResource(Res.string.movie_list_section_title_most_popular),
            uiState = uiState,
            content = { showMovieListsState ->
                if (showMovieListsState.popularMovieList.isEmpty()) {
                    EmptyState()
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(padding_16),
                        contentPadding = PaddingValues(horizontal = padding_8),
                    ) {
                        items(showMovieListsState.popularMovieList.size) { index ->
                            MostPopularMovieCard(
                                movieTitle = showMovieListsState.popularMovieList[index].title,
                                posterUrl = showMovieListsState.popularMovieList[index].posterPath,
                                onClick = {
                                    navigateToMovieDetail(showMovieListsState.popularMovieList[index])
                                },
                            )
                        }
                    }
                }
            },
        )
    }
}

@Composable
fun MovieListSection(
    modifier: Modifier = Modifier,
    title: String,
    uiState: MovieListState,
    content: @Composable (showMovieListsState: MovieListState.ShowMovieLists) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = movieListSectionTextStyle,
            modifier = Modifier.padding(bottom = padding_8),
        )
        when (uiState) {
            MovieListState.Idle -> {}
            MovieListState.Loading -> LoadingState()
            is MovieListState.Error -> ErrorState()
            is MovieListState.ShowMovieLists -> content(uiState)
        }
    }
}
