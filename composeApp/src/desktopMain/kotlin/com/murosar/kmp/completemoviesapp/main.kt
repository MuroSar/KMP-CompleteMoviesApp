package com.murosar.kmp.completemoviesapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.murosar.kmp.completemoviesapp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Complete movies app",
        ) {
            App()
        }
    }
}
