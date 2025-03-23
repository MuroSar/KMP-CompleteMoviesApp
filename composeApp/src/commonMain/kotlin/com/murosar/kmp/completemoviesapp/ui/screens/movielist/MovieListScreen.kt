package com.murosar.kmp.completemoviesapp.ui.screens.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieListScreen(
    navigateToMovieDetail: (movie: Movie) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Movies",
        )
        Button(
            onClick = { navigateToMovieDetail(Movie(1, "From movie list")) },
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
