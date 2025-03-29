package com.murosar.kmp.completemoviesapp.ui.preview.screen

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.screens.main.MainScreen

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
