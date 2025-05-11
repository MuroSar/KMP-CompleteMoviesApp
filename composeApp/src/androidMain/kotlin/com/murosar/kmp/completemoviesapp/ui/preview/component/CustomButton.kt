package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.CustomButton

@Preview
@Composable
fun AppButtonPrimaryPreview() {
    MaterialTheme {
        CustomButton(
            text = "Button",
            usePrimaryColor = true,
            onClick = { },
        )
    }
}

@Preview
@Composable
fun AppButtonSecondaryPreview() {
    MaterialTheme {
        CustomButton(
            text = "Button",
            usePrimaryColor = false,
            onClick = { },
        )
    }
}
