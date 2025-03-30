package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = koinViewModel<MovieDetailViewModel>(),
    movie: Movie?,
    movieId: Int,
    navigateBack: () -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetail(278)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Movie: ${movie?.id} - ${movie?.title}",
        )
        Text(
            text = "MovieID: $movieId",
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MaterialTheme {
        MovieDetailScreen(
            movie = Movie(
                id = 6449,
                adult = false,
                backdropPath = "commune",
                originalLanguage = "aliquam",
                originalTitle = "iudicabit",
                overview = "ultricies",
                popularity = 4.5,
                posterPath = "vestibulum",
                releaseDate = "cum",
                title = "veritus",
                video = false,
                voteAverage = 6.7,
                voteCount = 1580
            ),
            movieId = 1,
            navigateBack = { }
        )
    }
}
