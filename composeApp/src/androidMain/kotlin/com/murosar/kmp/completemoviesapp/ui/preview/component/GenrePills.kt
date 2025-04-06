package com.murosar.kmp.completemoviesapp.ui.preview.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.murosar.kmp.completemoviesapp.domain.model.Genre
import com.murosar.kmp.completemoviesapp.ui.component.GenrePills

@Preview
@Composable
fun GenrePillsPreview() {
    MaterialTheme {
        GenrePills(
            genres = listOf(
                Genre(id = 7450, name = "Rowena Woodward"),
                Genre(id = 3394, name = "Glenn Petty"),
                Genre(id = 6818, name = "Sonia Melendez"),
            )
        )
    }
}
