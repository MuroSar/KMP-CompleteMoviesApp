package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.TopRatedMovieCard

@Preview
@Composable
fun TopRatedMovieCardPreview() {
    MaterialTheme {
        TopRatedMovieCard(
            movieTitle = "Top Rated Movie",
            posterUrl = "some poster url",
            onClick = { },
        )
    }
}
