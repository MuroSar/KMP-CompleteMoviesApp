package com.murosar.kmp.completemoviesapp.ui.screens.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailScreen(
    movie: Movie?,
    movieId: Int,
) {
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
                id = 1,
                title = "Title",
            ),
            movieId = 1,
        )
    }
}
