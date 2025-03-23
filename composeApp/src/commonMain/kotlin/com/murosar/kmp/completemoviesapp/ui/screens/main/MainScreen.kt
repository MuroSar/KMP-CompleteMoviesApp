package com.murosar.kmp.completemoviesapp.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel { MainViewModel() },
    navigateToMovieList: () -> Unit,
    navigateToCharacterList: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navigateToMovieList() },
        ) {
            Text(
                text = "Movies",
            )
        }
        Button(
            onClick = { navigateToCharacterList() },
        ) {
            Text(
                text = "Characters",
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(
            navigateToMovieList = {},
            navigateToCharacterList = {},
        )
    }
}
