package com.murosar.kmp.completemoviesapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.murosar.kmp.completemoviesapp.ui.navigation.NavRoutes
import com.murosar.kmp.completemoviesapp.ui.navigation.addMoviesScreenGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    // Coil configuration
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }

    MaterialTheme {
        MoviesApp()
    }
}

@Composable
fun MoviesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.MainNavScreen) {
        addMoviesScreenGraph(navController)
    }
}
