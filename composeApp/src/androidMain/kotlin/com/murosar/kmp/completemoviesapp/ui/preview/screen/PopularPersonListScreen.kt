package com.murosar.kmp.completemoviesapp.ui.preview.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist.PopularPeopleContent
import com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist.PopularPersonListViewModel

@Preview
@Composable
fun PopularPersonListScreenPreview() {
    MaterialTheme {
        PopularPeopleContent(
            uiState =
                PopularPersonListViewModel.PopularPersonListState.ShowPopularPersonList(
                    listOf(
                        PopularPerson(
                            id = 3874,
                            adult = false,
                            gender = 8425,
                            knownFor = listOf(),
                            knownForDepartment = "qualisque",
                            name = "Melody Yang",
                            popularity = 10.11,
                            profilePath = "esse",
                        ),
                        PopularPerson(
                            id = 4631,
                            adult = false,
                            gender = 9954,
                            knownFor = listOf(),
                            knownForDepartment = "dolores",
                            name = "Tamika Sanders",
                            popularity = 14.15,
                            profilePath = "gubergren",
                        ),
                        PopularPerson(
                            id = 1840,
                            adult = false,
                            gender = 9843,
                            knownFor = listOf(),
                            knownForDepartment = "pretium",
                            name = "Viola Justice",
                            popularity = 6.7,
                            profilePath = "est",
                        ),
                    ),
                ),
            navigateToMovieDetail = { _ -> },
        )
    }
}

@Preview
@Composable
fun PopularPersonListScreenEmptyPreview() {
    MaterialTheme {
        PopularPeopleContent(
            uiState = PopularPersonListViewModel.PopularPersonListState.ShowPopularPersonList(emptyList()),
            navigateToMovieDetail = { _ -> },
        )
    }
}

@Preview
@Composable
fun PopularPersonListScreenLoadingPreview() {
    MaterialTheme {
        PopularPeopleContent(
            uiState = PopularPersonListViewModel.PopularPersonListState.Loading,
            navigateToMovieDetail = { _ -> },
        )
    }
}

@Preview
@Composable
fun PopularPersonListScreenErrorPreview() {
    MaterialTheme {
        PopularPeopleContent(
            uiState = PopularPersonListViewModel.PopularPersonListState.Error(MovieError.NoInternet),
            navigateToMovieDetail = { _ -> },
        )
    }
}
