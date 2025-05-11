package com.murosar.kmp.completemoviesapp

import androidx.compose.ui.window.ComposeUIViewController
import com.murosar.kmp.completemoviesapp.di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        },
    ) {
        App()
    }
