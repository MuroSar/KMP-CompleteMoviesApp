package com.murosar.kmp.completemoviesapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.murosar.kmp.completemoviesapp.ui.component.AppButton
import com.murosar.kmp.completemoviesapp.ui.theme.charcoal_black
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.titleStyle
import com.murosar.kmp.completemoviesapp.ui.theme.weight_02
import com.murosar.kmp.completemoviesapp.ui.theme.weight_03
import com.murosar.kmp.completemoviesapp.ui.theme.weight_05
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.main_screen_button_characters
import completemoviesapp.composeapp.generated.resources.main_screen_button_movies
import completemoviesapp.composeapp.generated.resources.main_screen_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(
    navigateToMovieList: () -> Unit,
    navigateToCharacterList: () -> Unit,
) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(charcoal_black, Color.Black)
    )

    Column(
        modifier = Modifier
            .background(backgroundBrush)
            .fillMaxSize()
            .padding(padding_16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.weight(weight_02))
        Text(
            text = stringResource(Res.string.main_screen_title),
            style = titleStyle
        )
        Spacer(modifier = Modifier.weight(weight_03))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            AppButton(
                text = stringResource(Res.string.main_screen_button_movies),
                onClick = navigateToMovieList
            )
            AppButton(
                modifier = Modifier.padding(top = padding_16),
                text = stringResource(Res.string.main_screen_button_characters),
                usePrimaryColor = false,
                onClick = navigateToCharacterList
            )
        }
        Spacer(modifier = Modifier.weight(weight_05))
    }
}
