package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.ui.component.StarRatingBar

@Preview
@Composable
fun StarRatingBarPreview() {
    MaterialTheme {
        StarRatingBar(
            rating = 3.5,
        )
    }
}
