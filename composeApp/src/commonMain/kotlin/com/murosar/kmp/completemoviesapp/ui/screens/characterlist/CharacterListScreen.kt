package com.murosar.kmp.completemoviesapp.ui.screens.characterlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterListScreen(
    navigateToMovieDetail: (movieId: Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Characters",
        )
        Button(
            onClick = { navigateToMovieDetail(9292) },
        ) {
            Text(
                text = "Detail",
            )
        }
    }
}

@Preview
@Composable
fun CharacterListScreenPreview() {
    MaterialTheme {
        CharacterListScreen(
            navigateToMovieDetail = { _ -> }
        )
    }
}
