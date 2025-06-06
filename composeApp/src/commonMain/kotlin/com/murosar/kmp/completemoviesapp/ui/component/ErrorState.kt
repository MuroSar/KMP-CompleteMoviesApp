package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.murosar.kmp.completemoviesapp.ui.theme.errorTextStyle
import com.murosar.kmp.completemoviesapp.ui.theme.padding_16
import com.murosar.kmp.completemoviesapp.ui.theme.padding_8
import com.murosar.kmp.completemoviesapp.ui.theme.size_100
import completemoviesapp.composeapp.generated.resources.Res
import completemoviesapp.composeapp.generated.resources.error_state_content_description
import completemoviesapp.composeapp.generated.resources.error_state_message
import completemoviesapp.composeapp.generated.resources.movie_error
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorState() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(padding_16),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(resource = Res.drawable.movie_error),
                contentDescription = stringResource(Res.string.error_state_content_description),
                modifier =
                    Modifier
                        .size(size_100)
                        .padding(bottom = padding_8),
            )
            Text(
                text = stringResource(Res.string.error_state_message),
                style = errorTextStyle,
                modifier = Modifier.padding(top = padding_8),
            )
        }
    }
}
