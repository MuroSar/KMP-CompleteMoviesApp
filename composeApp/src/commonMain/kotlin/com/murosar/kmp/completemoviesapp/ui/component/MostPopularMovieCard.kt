package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.height_140
import com.murosar.kmp.completemoviesapp.ui.theme.movieCardTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import com.murosar.kmp.completemoviesapp.ui.theme.rounded_corners_12
import com.murosar.kmp.completemoviesapp.ui.theme.width_100
import com.murosar.kmp.completemoviesapp.ui.theme.width_2
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.movie_error
import completemoviesapp.composeapp.generated.resources.movie_placeholder
import org.jetbrains.compose.resources.painterResource

@Composable
fun MostPopularMovieCard(
    movieTitle: String,
    posterUrl: String,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .wrapContentSize()
                .clickable { onClick() },
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .width(width_100)
                    .height(height_140)
                    .clip(RoundedCornerShape(rounded_corners_12))
                    .border(
                        width = width_2,
                        color = burnt_sienna,
                        shape = RoundedCornerShape(rounded_corners_12),
                    ),
            contentScale = ContentScale.FillBounds,
            model = "https://image.tmdb.org/t/p/w500$posterUrl",
            contentDescription = movieTitle,
            placeholder = painterResource(resource = Res.drawable.movie_placeholder),
            error = painterResource(resource = Res.drawable.movie_error),
        )
        Text(
            modifier =
                Modifier
                    .width(width_100)
                    .padding(top = padding_8),
            text = movieTitle,
            style = movieCardTextStyle,
            maxLines = 2,
        )
    }
}
