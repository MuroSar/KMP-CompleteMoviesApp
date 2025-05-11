package com.murosar.kmp.completemoviesapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

val backgroundBrush =
    Brush.verticalGradient(
        colors = listOf(charcoal_black, Color.Black),
    )

val topAppBarTextStyle =
    TextStyle(
        fontSize = font_size_20,
        fontWeight = FontWeight.Bold,
        color = golden_amber,
        textAlign = TextAlign.Center,
    )

val titleStyleWithGradient =
    TextStyle(
        fontSize = font_size_32,
        fontWeight = FontWeight.Bold,
        brush = Brush.linearGradient(colors = listOf(golden_amber, burnt_sienna)),
    )

val movieListSectionTextStyle =
    TextStyle(
        fontSize = font_size_24,
        fontWeight = FontWeight.Bold,
        color = burnt_sienna,
    )

val movieCardTextStyle =
    TextStyle(
        fontSize = font_size_12,
        fontWeight = FontWeight.Bold,
        color = pure_white,
        textAlign = TextAlign.Center,
    )

val loadingTextStyle =
    TextStyle(
        fontSize = font_size_16,
        fontWeight = FontWeight.Bold,
        color = pure_white,
        textAlign = TextAlign.Center,
    )

val errorTextStyle =
    TextStyle(
        fontSize = font_size_16,
        fontWeight = FontWeight.Bold,
        color = error_red,
        textAlign = TextAlign.Center,
    )

val emptyTextStyle =
    TextStyle(
        fontSize = font_size_16,
        fontWeight = FontWeight.Bold,
        color = steel_gray,
        textAlign = TextAlign.Center,
    )

val infoTextStyle =
    TextStyle(
        fontSize = font_size_14,
        fontWeight = FontWeight.Medium,
        color = steel_gray,
    )
