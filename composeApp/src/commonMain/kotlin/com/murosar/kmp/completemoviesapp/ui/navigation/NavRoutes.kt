package com.murosar.kmp.completemoviesapp.ui.navigation

import com.murosar.kmp.completemoviesapp.domain.utils.EMPTY_STRING
import com.murosar.kmp.completemoviesapp.domain.utils.MINUS_ONE_INT
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
        val movie: String = EMPTY_STRING,
        val movieId: Int = MINUS_ONE_INT,
    ) : NavRoutes()
}
