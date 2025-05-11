package com.murosar.kmp.completemoviesapp.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.ui.theme.backgroundBrush
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import completemoviesapp.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Splashcreen(navigateToMainScreen: () -> Unit) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/kmp_movie_splash.json").decodeToString(),
        )
    }
    val animationState =
        animateLottieCompositionAsState(
            composition = composition,
            iterations = 1,
            isPlaying = composition != null, // Ensures it only starts when the animation is charged
        )

    var hasStarted by remember { mutableStateOf(false) }
    var hasFinished by remember { mutableStateOf(false) }

    // Set animation start
    LaunchedEffect(animationState.isPlaying) {
        if (animationState.isPlaying) {
            hasStarted = true
        }
    }

    // Detects the end only if the animation has already started
    LaunchedEffect(animationState.isPlaying, hasStarted) {
        if (hasStarted && !animationState.isPlaying && !hasFinished) {
            hasFinished = true
            navigateToMainScreen()
        }
    }

    Column(
        modifier =
            Modifier
                .background(backgroundBrush)
                .fillMaxSize()
                .padding(padding_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter =
                rememberLottiePainter(
                    composition = composition,
                    progress = { animationState.progress },
                ),
            contentDescription = "Lottie animation",
        )
    }
}
