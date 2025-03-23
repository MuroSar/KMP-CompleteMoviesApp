package com.murosar.kmp.completemoviesapp.ui.navigation

import com.murosar.kmp.completemoviesapp.domain.model.Movie
import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    data object MainNavScreen : NavRoutes()

    @Serializable
    data object MovieListNavScreen : NavRoutes()

    @Serializable
    data object CharacterListNavScreen : NavRoutes()

    @Serializable
    data class MovieDetailNavScreen(
        val movie: String = "",
        val movieId: Int = -1,
    ) : NavRoutes()
}
