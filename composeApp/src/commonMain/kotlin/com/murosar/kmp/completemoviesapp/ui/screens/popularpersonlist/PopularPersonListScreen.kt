package com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PopularPersonListScreen(
    viewModel: PopularPersonListViewModel = koinViewModel<PopularPersonListViewModel>(),
    navigateToMovieDetail: (movieId: Int) -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.getPopularPeople()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Popular person",
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
fun PopularPersonListScreenPreview() {
    MaterialTheme {
        PopularPersonListScreen(
            navigateToMovieDetail = { _ -> }
        )
    }
}
