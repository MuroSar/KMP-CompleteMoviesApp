package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.murosar.kmp.completemoviesapp.domain.model.Genre
import com.murosar.kmp.completemoviesapp.ui.theme.deep_slate_gray
import com.murosar.kmp.completemoviesapp.ui.theme.infoTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import com.murosar.kmp.completemoviesapp.ui.theme.rounded_corners_16

@Composable
fun GenrePills(genres: List<Genre>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(padding_8),
        contentPadding = PaddingValues(top = padding_8),
    ) {
        items(genres.size) { index ->
            Box(
                modifier =
                    Modifier
                        .background(deep_slate_gray, RoundedCornerShape(rounded_corners_16))
                        .padding(horizontal = padding_16, vertical = padding_8),
            ) {
                Text(
                    text = genres[index].name,
                    style = infoTextStyle,
                    color = Color.White,
                )
            }
        }
    }
}
