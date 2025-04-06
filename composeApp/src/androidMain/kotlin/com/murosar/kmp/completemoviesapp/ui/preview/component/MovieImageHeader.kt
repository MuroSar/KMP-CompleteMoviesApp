package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.ui.component.MovieImageHeader

@Preview
@Composable
fun MovieImageHeaderPreview() {
    MaterialTheme {
        MovieImageHeader(
            movieDetail = MovieDetail(
                id = 1868,
                adult = false,
                backdropPath = "lorem",
                belongsToCollection = BelongsToCollection(
                    id = 1191,
                    name = "Bonnie Avila",
                    posterPath = "potenti",
                    backdropPath = "ipsum"
                ),
                budget = 8372,
                genres = listOf(),
                homepage = "urna",
                imdbId = "fringilla",
                originCountry = "China",
                originalLanguage = "alia",
                originalTitle = "ponderum",
                overview = "natoque",
                popularity = 4.5,
                posterPath = "ad",
                productionCompanies = listOf(),
                productionCountries = listOf(),
                releaseDate = "explicari",
                revenue = 3970,
                runtime = 6550,
                spokenLanguages = listOf(),
                status = "appareat",
                tagline = "neglegentur",
                title = "ignota",
                video = false,
                voteAverage = 6.7,
                voteCount = 5389
            )
        )
    }
}
