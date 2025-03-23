package com.murosar.kmp.completemoviesapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Complete movies app",
    ) {
        App()
    }
}