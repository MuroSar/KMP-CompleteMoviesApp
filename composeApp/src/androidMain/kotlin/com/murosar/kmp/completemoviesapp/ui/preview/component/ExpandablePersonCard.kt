package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.KnownFor
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.ui.component.ExpandablePersonCard

@Preview
@Composable
fun ExpandablePersonCardNonExpandedPreview() {
    MaterialTheme {
        ExpandablePersonCard(
            person = PopularPerson(
                id = 5693,
                adult = false,
                gender = 7696,
                knownFor = listOf(),
                knownForDepartment = "causae",
                name = "Helene Joseph",
                popularity = 2.3,
                profilePath = "melius"
            ),
            isExpanded = false,
            onExpandToggle = { },
            navigateToMovieDetail = { },
        )
    }
}

@Preview
@Composable
fun ExpandablePersonCardExpandedPreview() {
    MaterialTheme {
        ExpandablePersonCard(
            person = PopularPerson(
                id = 5693,
                adult = false,
                gender = 7696,
                knownFor = listOf(
                    KnownFor(
                        id = 8137,
                        adult = false,
                        backdropPath = "graeco",
                        firstAirDate = "feugait",
                        mediaType = "legimus",
                        name = "Logan Coffey",
                        originalLanguage = "quam",
                        originalName = "Mickey Farrell",
                        originalTitle = "pellentesque",
                        overview = "sumo",
                        popularity = 40.41,
                        posterPath = "torquent",
                        releaseDate = "ridiculus",
                        title = "lobortis",
                        video = false,
                        voteAverage = 42.43,
                        voteCount = 6211
                    ),
                    KnownFor(
                        id = 9564,
                        adult = false,
                        backdropPath = "tantas",
                        firstAirDate = "discere",
                        mediaType = "nisi",
                        name = "Salvatore Allen",
                        originalLanguage = "sonet",
                        originalName = "Morton Boyd",
                        originalTitle = "quisque",
                        overview = "salutatus",
                        popularity = 48.49,
                        posterPath = "adversarium",
                        releaseDate = "sanctus",
                        title = "velit",
                        video = false,
                        voteAverage = 50.51,
                        voteCount = 6091
                    ),
                    KnownFor(
                        id = 9634,
                        adult = false,
                        backdropPath = "instructior",
                        firstAirDate = "deserunt",
                        mediaType = "disputationi",
                        name = "Deloris George",
                        originalLanguage = "possim",
                        originalName = "Tommie Bass",
                        originalTitle = "mi",
                        overview = "inani",
                        popularity = 56.57,
                        posterPath = "ceteros",
                        releaseDate = "dico",
                        title = "tincidunt",
                        video = false,
                        voteAverage = 58.59,
                        voteCount = 2216
                    ),
                ),
                knownForDepartment = "causae",
                name = "Helene Joseph",
                popularity = 2.3,
                profilePath = "melius"
            ),
            isExpanded = true,
            onExpandToggle = { },
            navigateToMovieDetail = { },
        )
    }
}
