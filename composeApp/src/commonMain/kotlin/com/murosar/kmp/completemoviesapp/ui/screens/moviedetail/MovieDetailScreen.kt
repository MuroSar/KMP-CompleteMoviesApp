package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.ui.component.CustomTopAppBar
import com.murosar.kmp.completemoviesapp.ui.component.EmptyState
import com.murosar.kmp.completemoviesapp.ui.component.ErrorState
import com.murosar.kmp.completemoviesapp.ui.component.GenrePills
import com.murosar.kmp.completemoviesapp.ui.component.LoadingState
import com.murosar.kmp.completemoviesapp.ui.component.MostPopularMovieCard
import com.murosar.kmp.completemoviesapp.ui.component.MovieImageHeader
import com.murosar.kmp.completemoviesapp.ui.theme.backgroundBrush
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.height_140
import com.murosar.kmp.completemoviesapp.ui.theme.infoTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.movieListSectionTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_32
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import com.murosar.kmp.completemoviesapp.ui.theme.rounded_corners_12
import com.murosar.kmp.completemoviesapp.ui.theme.weight_1
import com.murosar.kmp.completemoviesapp.ui.theme.width_100
import com.murosar.kmp.completemoviesapp.ui.theme.width_2
import com.murosar.kmp.completemoviesapp.ui.util.toAmountFormat
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.movie_detail_budget
import completemoviesapp.composeapp.generated.resources.movie_detail_general_info_title
import completemoviesapp.composeapp.generated.resources.movie_detail_homepage
import completemoviesapp.composeapp.generated.resources.movie_detail_overview_title
import completemoviesapp.composeapp.generated.resources.movie_detail_popularity
import completemoviesapp.composeapp.generated.resources.movie_detail_recommended_title
import completemoviesapp.composeapp.generated.resources.movie_detail_release_date
import completemoviesapp.composeapp.generated.resources.movie_detail_revenue
import completemoviesapp.composeapp.generated.resources.movie_detail_runtime
import completemoviesapp.composeapp.generated.resources.movie_detail_title
import completemoviesapp.composeapp.generated.resources.movie_error
import completemoviesapp.composeapp.generated.resources.movie_placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = koinViewModel<MovieDetailViewModel>(),
    movie: Movie?,
    movieId: Int,
    navigateToMovieDetail: (movieId: Int) -> Unit,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetail(movie?.id ?: movieId)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(Res.string.movie_detail_title),
                onBackClick = navigateBack,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        MovieDetailContent(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            navigateToMovieDetail = { movieId -> navigateToMovieDetail(movieId) },
        )
    }
}

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    uiState: MovieDetailViewModel.MovieDetailState,
    navigateToMovieDetail: (movieId: Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(backgroundBrush),
    ) {
        when (uiState) {
            MovieDetailViewModel.MovieDetailState.Idle -> {}
            MovieDetailViewModel.MovieDetailState.Loading -> LoadingState()
            is MovieDetailViewModel.MovieDetailState.Error -> ErrorState()
            is MovieDetailViewModel.MovieDetailState.ShowMovieDetail -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                ) {
                    MovieImageHeader(uiState.movieDetail)

                    Column(
                        modifier = Modifier.padding(padding_16),
                    ) {
                        if (uiState.movieDetail.genres.isNotEmpty()) {
                            GenrePills(genres = uiState.movieDetail.genres)
                        }
                        Text(
                            modifier = Modifier.padding(top = padding_16, bottom = padding_8),
                            text = stringResource(Res.string.movie_detail_general_info_title),
                            style = movieListSectionTextStyle,
                        )
                        if (uiState.movieDetail.homepage.isNotEmpty()) {
                            Text(
                                text = stringResource(Res.string.movie_detail_homepage, uiState.movieDetail.homepage),
                                style = infoTextStyle,
                            )
                        }
                        Text(
                            text = stringResource(Res.string.movie_detail_release_date, uiState.movieDetail.releaseDate),
                            style = infoTextStyle,
                        )
                        Text(
                            text = stringResource(Res.string.movie_detail_popularity, uiState.movieDetail.popularity),
                            style = infoTextStyle,
                        )
                        Text(
                            text = stringResource(Res.string.movie_detail_runtime, uiState.movieDetail.runtime),
                            style = infoTextStyle,
                        )
                        Text(
                            text = stringResource(Res.string.movie_detail_budget, uiState.movieDetail.budget.toAmountFormat()),
                            style = infoTextStyle,
                        )
                        Text(
                            text = stringResource(Res.string.movie_detail_revenue, uiState.movieDetail.revenue.toAmountFormat()),
                            style = infoTextStyle,
                        )
                        if (uiState.movieCollection != null) {
                            Text(
                                text = uiState.movieCollection.name,
                                style = movieListSectionTextStyle,
                                modifier = Modifier.padding(top = padding_16, bottom = padding_8),
                            )
                            Row {
                                AsyncImage(
                                    modifier =
                                        Modifier
                                            .width(width_100)
                                            .height(height_140)
                                            .clip(RoundedCornerShape(rounded_corners_12))
                                            .border(
                                                width = width_2,
                                                color = burnt_sienna,
                                                shape = RoundedCornerShape(rounded_corners_12),
                                            ),
                                    contentScale = ContentScale.FillBounds,
                                    model = "https://image.tmdb.org/t/p/w500${uiState.movieCollection.posterPath}",
                                    contentDescription = uiState.movieCollection.name,
                                    placeholder = painterResource(resource = Res.drawable.movie_placeholder),
                                    error = painterResource(resource = Res.drawable.movie_error),
                                )
                                Text(
                                    modifier =
                                        Modifier
                                            .padding(start = padding_8)
                                            .height(height_140)
                                            .verticalScroll(rememberScrollState()),
                                    text = uiState.movieCollection.overview,
                                    style = infoTextStyle,
                                )
                            }
                        }

                        Text(
                            text = stringResource(Res.string.movie_detail_overview_title),
                            style = movieListSectionTextStyle,
                            modifier = Modifier.padding(top = padding_16, bottom = padding_8),
                        )
                        Text(
                            text = uiState.movieDetail.overview,
                            style = infoTextStyle,
                        )
                        Spacer(modifier = Modifier.weight(weight_1))
                        if (uiState.recommendedMovieList.isNotEmpty()) {
                            Text(
                                text = stringResource(Res.string.movie_detail_recommended_title),
                                style = movieListSectionTextStyle,
                                modifier = Modifier.padding(top = padding_32, bottom = padding_8),
                            )

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(padding_16),
                                contentPadding = PaddingValues(horizontal = padding_8),
                            ) {
                                items(uiState.recommendedMovieList) { recommended ->
                                    MostPopularMovieCard(
                                        movieTitle = recommended.title,
                                        posterUrl = recommended.posterPath,
                                        onClick = { navigateToMovieDetail(recommended.id) },
                                    )
                                }
                            }
                        } else {
                            EmptyState()
                        }
                    }
                }
            }
        }
    }
}
