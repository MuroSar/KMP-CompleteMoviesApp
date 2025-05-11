package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import coil3.compose.AsyncImage
import com.murosar.kmp.completemoviesapp.domain.model.MovieDetail
import com.murosar.kmp.completemoviesapp.domain.utils.TWO_INT
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.charcoal_black
import com.murosar.kmp.completemoviesapp.ui.theme.height_200
import com.murosar.kmp.completemoviesapp.ui.theme.infoTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.movieListSectionTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_4
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.movie_detail_header_id
import completemoviesapp.composeapp.generated.resources.movie_detail_header_vote_average
import completemoviesapp.composeapp.generated.resources.movie_error
import completemoviesapp.composeapp.generated.resources.movie_placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieImageHeader(movieDetail: MovieDetail) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(height_200),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://image.tmdb.org/t/p/w500${movieDetail.backdropPath}",
            contentDescription = movieDetail.title,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(resource = Res.drawable.movie_placeholder),
            error = painterResource(resource = Res.drawable.movie_error),
        )
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, charcoal_black),
                            startY = 0f,
                            endY = with(LocalDensity.current) { height_200.toPx() },
                        ),
                    ),
        )
        Text(
            modifier =
                Modifier
                    .padding(top = padding_8, end = padding_8)
                    .align(Alignment.TopEnd),
            text = stringResource(Res.string.movie_detail_header_id, movieDetail.id.toString()),
            style = infoTextStyle,
            color = burnt_sienna,
        )

        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(padding_16),
        ) {
            Text(
                text = movieDetail.title,
                style = movieListSectionTextStyle,
                color = Color.White,
            )
            Text(
                modifier = Modifier.padding(top = padding_4),
                text = movieDetail.tagline,
                style = infoTextStyle,
                color = burnt_sienna,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StarRatingBar(
                    modifier = Modifier.padding(top = padding_4),
                    rating = movieDetail.voteAverage / TWO_INT,
                )
                Text(
                    modifier = Modifier.padding(start = padding_8, top = padding_4),
                    text = stringResource(Res.string.movie_detail_header_vote_average, movieDetail.voteAverage.toString()),
                    style = infoTextStyle,
                    color = burnt_sienna,
                )
            }
        }
    }
}
