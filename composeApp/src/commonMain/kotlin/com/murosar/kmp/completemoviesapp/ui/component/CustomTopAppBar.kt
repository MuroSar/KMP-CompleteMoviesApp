package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.murosar.kmp.completemoviesapp.ui.theme.charcoal_black
import com.murosar.kmp.completemoviesapp.ui.theme.elevation_4
import com.murosar.kmp.completemoviesapp.ui.theme.golden_amber
import com.murosar.kmp.completemoviesapp.ui.theme.topAppBarTextStyle
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.back_content_description
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    onBackClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    text = title,
                    style = topAppBarTextStyle,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back_content_description),
                    tint = golden_amber,
                )
            }
        },
        actions = {
            // Transparent icon to balance the layout and center the title
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.Transparent,
            )
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = charcoal_black,
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = elevation_4,
                    shape = RectangleShape,
                    ambientColor = golden_amber,
                    spotColor = golden_amber,
                ),
    )
}
