package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.ui.theme.loadingTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.loading_state_message
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoadingState() {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/kmp_movie_loader.json").decodeToString(),
        )
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(padding_16),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter =
                    rememberLottiePainter(
                        composition = composition,
                        iterations = Compottie.IterateForever,
                    ),
                contentDescription = "Lottie animation",
            )
            Text(
                text = stringResource(Res.string.loading_state_message),
                style = loadingTextStyle,
                modifier = Modifier.padding(top = padding_8),
            )
        }
    }
}
