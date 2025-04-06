package com.murosar.kmp.completemoviesapp.ui.preview.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.BelongsToCollection
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.domain.model.MovieCollection
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.model.MovieError
import com.murosar.kmp.completemoviesapp.ui.screens.moviedetail.MovieDetailContent
import com.murosar.kmp.completemoviesapp.ui.screens.moviedetail.MovieDetailViewModel

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                movieDetail = MovieDetail(
                    id = 3935,
                    adult = false,
                    backdropPath = "aptent",
                    belongsToCollection = BelongsToCollection(
                        id = 4522,
                        name = "Malinda Hahn",
                        posterPath = "his", 
                        backdropPath = "per"
                    ),
                    budget = 8806,
                    genres = listOf(),
                    homepage = "solum",
                    imdbId = "prodesset",
                    originCountry = "Guinea",
                    originalLanguage = "fastidii",
                    originalTitle = "dictas",
                    overview = "curabitur",
                    popularity = 12.13,
                    posterPath = "delectus",
                    productionCompanies = listOf(),
                    productionCountries = listOf(),
                    releaseDate = "tractatos",
                    revenue = 3225,
                    runtime = 1294,
                    spokenLanguages = listOf(),
                    status = "nullam",
                    tagline = "dicam",
                    title = "inceptos",
                    video = false,
                    voteAverage = 14.15,
                    voteCount = 7988
                ),
                movieCollection = MovieCollection(
                    id = 4287,
                    adult = false,
                    backdropPath = "scelerisque",
                    name = "Beulah Craft",
                    originalLanguage = "fringilla",
                    originalName = "Rufus Stewart",
                    overview = "porro fringilla scelerisque",
                    posterPath = "quam"
                ),
                recommendedMovieList = listOf(
                    Movie(
                        id = 1824,
                        adult = false,
                        backdropPath = "iudicabit",
                        originalLanguage = "habemus",
                        originalTitle = "dicam",
                        overview = "percipit",
                        popularity = 36.37,
                        posterPath = "enim",
                        releaseDate = "utroque",
                        title = "recteque",
                        video = false,
                        voteAverage = 38.39,
                        voteCount = 1473
                    ),
                    Movie(
                        id = 7180,
                        adult = false,
                        backdropPath = "vidisse",
                        originalLanguage = "solet",
                        originalTitle = "tacimates",
                        overview = "fuisset",
                        popularity = 28.29,
                        posterPath = "contentiones",
                        releaseDate = "primis",
                        title = "bibendum",
                        video = false,
                        voteAverage = 30.31,
                        voteCount = 4845
                    ),
                    Movie(
                        id = 3885,
                        adult = false,
                        backdropPath = "ornatus",
                        originalLanguage = "inceptos",
                        originalTitle = "veniam",
                        overview = "utamur",
                        popularity = 20.21,
                        posterPath = "ipsum",
                        releaseDate = "finibus",
                        title = "orci",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 4074
                    )
                )
            ),
            navigateToMovieDetail = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenPreviewWithoutCollection() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                movieDetail = MovieDetail(
                    id = 3935,
                    adult = false,
                    backdropPath = "aptent",
                    belongsToCollection = BelongsToCollection(
                        id = 4522,
                        name = "Malinda Hahn",
                        posterPath = "his",
                        backdropPath = "per"
                    ),
                    budget = 8806,
                    genres = listOf(),
                    homepage = "solum",
                    imdbId = "prodesset",
                    originCountry = "Guinea",
                    originalLanguage = "fastidii",
                    originalTitle = "dictas",
                    overview = "curabitur",
                    popularity = 12.13,
                    posterPath = "delectus",
                    productionCompanies = listOf(),
                    productionCountries = listOf(),
                    releaseDate = "tractatos",
                    revenue = 3225,
                    runtime = 1294,
                    spokenLanguages = listOf(),
                    status = "nullam",
                    tagline = "dicam",
                    title = "inceptos",
                    video = false,
                    voteAverage = 14.15,
                    voteCount = 7988
                ),
                recommendedMovieList = listOf(
                    Movie(
                        id = 1824,
                        adult = false,
                        backdropPath = "iudicabit",
                        originalLanguage = "habemus",
                        originalTitle = "dicam",
                        overview = "percipit",
                        popularity = 36.37,
                        posterPath = "enim",
                        releaseDate = "utroque",
                        title = "recteque",
                        video = false,
                        voteAverage = 38.39,
                        voteCount = 1473
                    ),
                    Movie(
                        id = 7180,
                        adult = false,
                        backdropPath = "vidisse",
                        originalLanguage = "solet",
                        originalTitle = "tacimates",
                        overview = "fuisset",
                        popularity = 28.29,
                        posterPath = "contentiones",
                        releaseDate = "primis",
                        title = "bibendum",
                        video = false,
                        voteAverage = 30.31,
                        voteCount = 4845
                    ),
                    Movie(
                        id = 3885,
                        adult = false,
                        backdropPath = "ornatus",
                        originalLanguage = "inceptos",
                        originalTitle = "veniam",
                        overview = "utamur",
                        popularity = 20.21,
                        posterPath = "ipsum",
                        releaseDate = "finibus",
                        title = "orci",
                        video = false,
                        voteAverage = 22.23,
                        voteCount = 4074
                    )
                )
            ),
            navigateToMovieDetail = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenPreviewWithoutRecommendations() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                movieDetail = MovieDetail(
                    id = 3935,
                    adult = false,
                    backdropPath = "aptent",
                    belongsToCollection = BelongsToCollection(
                        id = 4522,
                        name = "Malinda Hahn",
                        posterPath = "his",
                        backdropPath = "per"
                    ),
                    budget = 8806,
                    genres = listOf(),
                    homepage = "solum",
                    imdbId = "prodesset",
                    originCountry = "Guinea",
                    originalLanguage = "fastidii",
                    originalTitle = "dictas",
                    overview = "curabitur",
                    popularity = 12.13,
                    posterPath = "delectus",
                    productionCompanies = listOf(),
                    productionCountries = listOf(),
                    releaseDate = "tractatos",
                    revenue = 3225,
                    runtime = 1294,
                    spokenLanguages = listOf(),
                    status = "nullam",
                    tagline = "dicam",
                    title = "inceptos",
                    video = false,
                    voteAverage = 14.15,
                    voteCount = 7988
                ),
                movieCollection = MovieCollection(
                    id = 4287,
                    adult = false,
                    backdropPath = "scelerisque",
                    name = "Beulah Craft",
                    originalLanguage = "fringilla",
                    originalName = "Rufus Stewart",
                    overview = "porro fringilla scelerisque",
                    posterPath = "quam"
                ),
                recommendedMovieList = emptyList()
            ),
            navigateToMovieDetail = { }
        )
    }
}


@Preview
@Composable
fun MovieDetailScreenEmptyPreview() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.ShowMovieDetail(
                movieDetail = MovieDetail(
                    id = 3935,
                    adult = false,
                    backdropPath = "aptent",
                    belongsToCollection = BelongsToCollection(
                        id = 6138,
                        name = "Darrell Cameron",
                        posterPath = "dicta",
                        backdropPath = "in"
                    ),
                    budget = 8806,
                    genres = listOf(),
                    homepage = "solum",
                    imdbId = "prodesset",
                    originCountry = "Guinea",
                    originalLanguage = "fastidii",
                    originalTitle = "dictas",
                    overview = "curabitur",
                    popularity = 12.13,
                    posterPath = "delectus",
                    productionCompanies = listOf(),
                    productionCountries = listOf(),
                    releaseDate = "tractatos",
                    revenue = 3225,
                    runtime = 1294,
                    spokenLanguages = listOf(),
                    status = "nullam",
                    tagline = "dicam",
                    title = "inceptos",
                    video = false,
                    voteAverage = 14.15,
                    voteCount = 7988
                ),
                recommendedMovieList = emptyList()
            ),
            navigateToMovieDetail = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenLoadingPreview() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.Loading,
            navigateToMovieDetail = { },
        )
    }
}

@Preview
@Composable
fun MovieDetailScreenErrorPreview() {
    MaterialTheme {
        MovieDetailContent(
            uiState = MovieDetailViewModel.MovieDetailState.Error(MovieError.NoInternet),
            navigateToMovieDetail = { },
        )
    }
}
