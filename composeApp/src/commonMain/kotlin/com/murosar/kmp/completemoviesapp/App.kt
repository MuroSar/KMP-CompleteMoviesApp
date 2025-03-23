package com.murosar.kmp.completemoviesapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.murosar.kmp.completemoviesapp.ui.navigation.NavRoutes
import com.murosar.kmp.completemoviesapp.ui.navigation.addMoviesScreenGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
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
