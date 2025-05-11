package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.EmptyState

@Preview
@Composable
fun MovieEmptyStatePreview() {
    MaterialTheme {
        EmptyState(
            isMovieEmptyState = true,
        )
    }
}

@Preview
@Composable
fun PopularPersonEmptyStatePreview() {
    MaterialTheme {
        EmptyState(
            isMovieEmptyState = false,
        )
    }
}
