package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.AppButton

@Preview
@Composable
fun AppButtonPrimaryPreview() {
    MaterialTheme {
        AppButton(
            text = "Button",
            usePrimaryColor = true,
            onClick = { }
        )
    }
}

@Preview
@Composable
fun AppButtonSecondaryPreview() {
    MaterialTheme {
        AppButton(
            text = "Button",
            usePrimaryColor = false,
            onClick = { }
        )
    }
}
