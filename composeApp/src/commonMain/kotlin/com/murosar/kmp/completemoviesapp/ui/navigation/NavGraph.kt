package com.murosar.kmp.completemoviesapp.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.murosar.kmp.completemoviesapp.domain.model.Movie
import com.murosar.kmp.completemoviesapp.ui.screens.main.MainScreen
import com.murosar.kmp.completemoviesapp.ui.screens.moviedetail.MovieDetailScreen
import com.murosar.kmp.completemoviesapp.ui.screens.movielist.MovieListScreen
import com.murosar.kmp.completemoviesapp.ui.screens.popularpersonlist.PopularPersonListScreen
import com.murosar.kmp.completemoviesapp.ui.screens.splash.Splashcreen
import kotlinx.serialization.json.Json

fun NavGraphBuilder.addMoviesScreenGraph(navController: NavController) {
    composable<NavRoutes.SplashNavScreen> {
        Splashcreen(
            navigateToMainScreen = {
                navController.navigate(NavRoutes.MainNavScreen) {
                    popUpTo(NavRoutes.SplashNavScreen) {
                        // Indicates how far up the backstack should be cleared
                        inclusive = true // Includes the SplashScreen in the backstack removal
                    }
                    launchSingleTop = true // Prevents multiple instances of the MainScreen if it's already on top.
                }
            },
        )
    }
    composable<NavRoutes.MainNavScreen> {
        MainScreen(
            navigateToMovieList = { navController.navigate(NavRoutes.MovieListNavScreen) },
            navigateToCharacterList = { navController.navigate(NavRoutes.CharacterListNavScreen) },
        )
    }
    composable<NavRoutes.MovieListNavScreen> {
        MovieListScreen(
            navigateToMovieDetail = { movie: Movie ->
                navController.navigate(NavRoutes.MovieDetailNavScreen(movie = Json.encodeToString(Movie.serializer(), movie)))
            },
            navigateBack = { navController.popBackStack() },
        )
    }
    composable<NavRoutes.CharacterListNavScreen> {
        PopularPersonListScreen(
            navigateToMovieDetail = { movieId -> navController.navigate(NavRoutes.MovieDetailNavScreen(movieId = movieId)) },
            navigateBack = { navController.popBackStack() },
        )
    }
    /**
     * Example of hoy to pass arguments to a composable
     * - movie is a complex object, Movie
     * - movieId is a primitive type, Int
     */
    composable<NavRoutes.MovieDetailNavScreen> {
        MovieDetailScreen(
            movie =
                it
                    .toRoute<NavRoutes.MovieDetailNavScreen>()
                    .movie
                    .takeIf { movieJson -> movieJson.isNotEmpty() }
                    ?.let { movieJson -> Json.decodeFromString(Movie.serializer(), movieJson) },
            movieId = it.toRoute<NavRoutes.MovieDetailNavScreen>().movieId,
            navigateToMovieDetail = { movieId -> navController.navigate(NavRoutes.MovieDetailNavScreen(movieId = movieId)) },
            navigateBack = { navController.popBackStack() },
        )
    }
}
