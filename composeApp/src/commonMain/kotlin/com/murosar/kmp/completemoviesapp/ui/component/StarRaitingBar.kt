package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.steel_gray

@Composable
fun StarRatingBar(
    modifier: Modifier = Modifier,
    rating: Double, // 0.0 - 5.0
    starSize: Dp = 16.dp,
    spaceBetween: Dp = 2.dp,
) {
    val maxStars = 5
    val fullStars = rating.toInt()
    val partial = (rating - fullStars).coerceIn(0.0, 1.0)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until maxStars) {
            Box(modifier = Modifier.size(starSize)) {
                // Star border (empty)
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = steel_gray,
                    modifier = Modifier.matchParentSize(),
                )

                // Star fill (only if needed)
                when {
                    i < fullStars -> {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = burnt_sienna,
                            modifier = Modifier.matchParentSize(),
                        )
                    }

                    i == fullStars && partial > 0.0 -> {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = burnt_sienna,
                            modifier =
                                Modifier
                                    .matchParentSize()
                                    .clip(RectangleShape)
                                    .graphicsLayer {
                                        clip = true
                                        shape = RectangleShape
                                    }.drawWithContent {
                                        val width = size.width * partial.toFloat()
                                        clipRect(right = width) {
                                            this@drawWithContent.drawContent()
                                        }
                                    },
                        )
                    }
                }
            }

            if (i < maxStars - 1) {
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }
    }
}
