package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.deep_slate_gray
import com.murosar.kmp.completemoviesapp.ui.theme.infoTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.movieCardTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.movieListSectionTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import com.murosar.kmp.completemoviesapp.ui.theme.rounded_corners_12
import com.murosar.kmp.completemoviesapp.ui.theme.width_2
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.movie_error
import completemoviesapp.composeapp.generated.resources.movie_placeholder
import completemoviesapp.composeapp.generated.resources.popular_person_department
import completemoviesapp.composeapp.generated.resources.popular_person_id
import completemoviesapp.composeapp.generated.resources.popular_person_known_for
import completemoviesapp.composeapp.generated.resources.popular_person_popularity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpandablePersonCard(
    person: PopularPerson,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    navigateToMovieDetail: (movieId: Int) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(rounded_corners_12))
                .background(deep_slate_gray)
                .clickable { onExpandToggle() }
                .padding(padding_16),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier =
                    Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(width_2, burnt_sienna, CircleShape),
                model = "https://image.tmdb.org/t/p/w500${person.profilePath}",
                contentDescription = person.name,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(resource = Res.drawable.movie_placeholder),
                error = painterResource(resource = Res.drawable.movie_error),
            )
            Spacer(modifier = Modifier.width(padding_16))
            Column {
                Text(
                    text = person.name,
                    style = movieCardTextStyle,
                )
                Text(
                    text = stringResource(Res.string.popular_person_popularity, person.popularity.toString()),
                    style = infoTextStyle,
                )
                Text(
                    text = stringResource(Res.string.popular_person_department, person.knownForDepartment),
                    style = infoTextStyle,
                )
            }
        }

        AnimatedVisibility(isExpanded) {
            Column(modifier = Modifier.padding(top = padding_8)) {
                Text(
                    text = stringResource(Res.string.popular_person_id, person.id.toString()),
                    style = infoTextStyle,
                )
                Text(
                    modifier = Modifier.padding(top = padding_8),
                    text = stringResource(Res.string.popular_person_known_for),
                    style = movieListSectionTextStyle,
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(padding_8)) {
                    items(person.knownFor) { knownForMovie ->
                        MostPopularMovieCard(
                            movieTitle = knownForMovie.title.ifEmpty { knownForMovie.name },
                            posterUrl = knownForMovie.posterPath,
                            onClick = { navigateToMovieDetail(knownForMovie.id) },
                        )
                    }
                }
            }
        }
    }
}
