package com.murosar.kmp.completemoviesapp.ui.preview.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.ui.screens.movielist.MovieListContent
import com.murosar.kmp.completemoviesapp.ui.screens.movielist.MovieListViewModel

@Preview
@Composable
fun MovieListScreenPreview() {
    MaterialTheme {
        MovieListContent(
            modifier = Modifier,
            uiState =
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList =
                        listOf(
                            Movie(
                                id = 1824,
                                adult = false,
                                backdropPath = "iudicabit",
                                originalLanguage = "habemus",
                                originalTitle = "dicam",
                                overview = "percipit",
                                popularity = 36.37,
                                posterPath = "enim",
                                releaseDate = "utroque",
                                title = "recteque",
                                video = false,
                                voteAverage = 38.39,
                                voteCount = 1473,
                            ),
                            Movie(
                                id = 7180,
                                adult = false,
                                backdropPath = "vidisse",
                                originalLanguage = "solet",
                                originalTitle = "tacimates",
                                overview = "fuisset",
                                popularity = 28.29,
                                posterPath = "contentiones",
                                releaseDate = "primis",
                                title = "bibendum",
                                video = false,
                                voteAverage = 30.31,
                                voteCount = 4845,
                            ),
                            Movie(
                                id = 3885,
                                adult = false,
                                backdropPath = "ornatus",
                                originalLanguage = "inceptos",
                                originalTitle = "veniam",
                                overview = "utamur",
                                popularity = 20.21,
                                posterPath = "ipsum",
                                releaseDate = "finibus",
                                title = "orci",
                                video = false,
                                voteAverage = 22.23,
                                voteCount = 4074,
                            ),
                            Movie(
                                id = 2508,
                                adult = false,
                                backdropPath = "repudiare",
                                originalLanguage = "finibus",
                                originalTitle = "suscipit",
                                overview = "ei",
                                popularity = 12.13,
                                posterPath = "purus",
                                releaseDate = "singulis",
                                title = "velit",
                                video = false,
                                voteAverage = 14.15,
                                voteCount = 3883,
                            ),
                        ),
                    topRatedMovieList =
                        listOf(
                            Movie(
                                id = 1824,
                                adult = false,
                                backdropPath = "iudicabit",
                                originalLanguage = "habemus",
                                originalTitle = "dicam",
                                overview = "percipit",
                                popularity = 36.37,
                                posterPath = "enim",
                                releaseDate = "utroque",
                                title = "recteque",
                                video = false,
                                voteAverage = 38.39,
                                voteCount = 1473,
                            ),
                            Movie(
                                id = 7180,
                                adult = false,
                                backdropPath = "vidisse",
                                originalLanguage = "solet",
                                originalTitle = "tacimates",
                                overview = "fuisset",
                                popularity = 28.29,
                                posterPath = "contentiones",
                                releaseDate = "primis",
                                title = "bibendum",
                                video = false,
                                voteAverage = 30.31,
                                voteCount = 4845,
                            ),
                            Movie(
                                id = 3885,
                                adult = false,
                                backdropPath = "ornatus",
                                originalLanguage = "inceptos",
                                originalTitle = "veniam",
                                overview = "utamur",
                                popularity = 20.21,
                                posterPath = "ipsum",
                                releaseDate = "finibus",
                                title = "orci",
                                video = false,
                                voteAverage = 22.23,
                                voteCount = 4074,
                            ),
                            Movie(
                                id = 2508,
                                adult = false,
                                backdropPath = "repudiare",
                                originalLanguage = "finibus",
                                originalTitle = "suscipit",
                                overview = "ei",
                                popularity = 12.13,
                                posterPath = "purus",
                                releaseDate = "singulis",
                                title = "velit",
                                video = false,
                                voteAverage = 14.15,
                                voteCount = 3883,
                            ),
                        ),
                    upcomingMovieList =
                        listOf(
                            Movie(
                                id = 1824,
                                adult = false,
                                backdropPath = "iudicabit",
                                originalLanguage = "habemus",
                                originalTitle = "dicam",
                                overview = "percipit",
                                popularity = 36.37,
                                posterPath = "enim",
                                releaseDate = "utroque",
                                title = "recteque",
                                video = false,
                                voteAverage = 38.39,
                                voteCount = 1473,
                            ),
                            Movie(
                                id = 7180,
                                adult = false,
                                backdropPath = "vidisse",
                                originalLanguage = "solet",
                                originalTitle = "tacimates",
                                overview = "fuisset",
                                popularity = 28.29,
                                posterPath = "contentiones",
                                releaseDate = "primis",
                                title = "bibendum",
                                video = false,
                                voteAverage = 30.31,
                                voteCount = 4845,
                            ),
                            Movie(
                                id = 3885,
                                adult = false,
                                backdropPath = "ornatus",
                                originalLanguage = "inceptos",
                                originalTitle = "veniam",
                                overview = "utamur",
                                popularity = 20.21,
                                posterPath = "ipsum",
                                releaseDate = "finibus",
                                title = "orci",
                                video = false,
                                voteAverage = 22.23,
                                voteCount = 4074,
                            ),
                            Movie(
                                id = 2508,
                                adult = false,
                                backdropPath = "repudiare",
                                originalLanguage = "finibus",
                                originalTitle = "suscipit",
                                overview = "ei",
                                popularity = 12.13,
                                posterPath = "purus",
                                releaseDate = "singulis",
                                title = "velit",
                                video = false,
                                voteAverage = 14.15,
                                voteCount = 3883,
                            ),
                        ),
                ),
            navigateToMovieDetail = { },
        )
    }
}

@Preview
@Composable
fun MovieListScreenEmptyPreview() {
    MaterialTheme {
        MovieListContent(
            modifier = Modifier,
            uiState =
                MovieListViewModel.MovieListState.ShowMovieLists(
                    popularMovieList = emptyList(),
                    topRatedMovieList = emptyList(),
                    upcomingMovieList = emptyList(),
                ),
            navigateToMovieDetail = { },
        )
    }
}

@Preview
@Composable
fun MovieListScreenLoadingPreview() {
    MaterialTheme {
        MovieListContent(
            modifier = Modifier,
            uiState = MovieListViewModel.MovieListState.Loading,
            navigateToMovieDetail = { },
        )
    }
}

@Preview
@Composable
fun MovieListScreenErrorPreview() {
    MaterialTheme {
        MovieListContent(
            modifier = Modifier,
            uiState = MovieListViewModel.MovieListState.Error(MovieError.NoInternet),
            navigateToMovieDetail = { },
        )
    }
}
