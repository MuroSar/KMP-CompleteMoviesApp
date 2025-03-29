package com.murosar.kmp.completemoviesapp.ui.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import com.murosar.kmp.completemoviesapp.ui.theme.antique_gold
import com.murosar.kmp.completemoviesapp.ui.theme.burnt_sienna
import com.murosar.kmp.completemoviesapp.ui.theme.font_size_18
import com.murosar.kmp.completemoviesapp.ui.theme.height_50
import com.murosar.kmp.completemoviesapp.ui.theme.pure_white
import com.murosar.kmp.completemoviesapp.ui.theme.rounded_corners_12
import com.murosar.kmp.completemoviesapp.ui.theme.scale_095
import com.murosar.kmp.completemoviesapp.ui.theme.scale_1

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    usePrimaryColor: Boolean = true,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(height_50)
            .scale(if (isPressed) scale_095 else scale_1)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        awaitRelease()
                        isPressed = false
                    }
                )
            },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (usePrimaryColor) burnt_sienna else antique_gold,
            contentColor = pure_white
        ),
        shape = RoundedCornerShape(rounded_corners_12),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = font_size_18,
            fontWeight = FontWeight.Bold
        )
    }
}
