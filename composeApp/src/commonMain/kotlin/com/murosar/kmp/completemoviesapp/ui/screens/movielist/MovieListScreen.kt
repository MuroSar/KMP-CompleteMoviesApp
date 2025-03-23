package com.murosar.kmp.completemoviesapp.ui.screens.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = koinViewModel<MovieListViewModel>(),
    navigateToMovieDetail: (movie: Movie) -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Movies",
        )
        Button(
            onClick = {
                navigateToMovieDetail(
                    Movie(
                        id = 4153,
                        adult = false,
                        backdropPath = "constituto",
                        originalLanguage = "integer",
                        originalTitle = "nonumy",
                        overview = "ad",
                        popularity = 12.13,
                        posterPath = "omittam",
                        releaseDate = "senectus",
                        title = "adipisci",
                        video = false,
                        voteAverage = 14.15,
                        voteCount = 4406
                    )
                )
            },
        ) {
            Text(
                text = "Detail",
            )
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    MaterialTheme {
        MovieListScreen(
            navigateToMovieDetail = { _ -> }
        )
    }
}
