package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.MostPopularMovieCard

@Preview
@Composable
fun MostPopularMovieCardPreview() {
    MaterialTheme {
        MostPopularMovieCard(
            movieTitle = "Most Popular Movie",
            posterUrl = "some poster url",
            onClick = { },
        )
    }
}
