package com.na.coworking.ui.global

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R

@Composable
fun RedButton(
    text: String,
    onClick: () -> Unit,

    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    padding: Dp = 15.dp,
    isEnabled: Boolean = true
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray),
                enabled = isEnabled
            )
            .shadow(4.dp, RoundedCornerShape(5.dp))
            .background(
                color = colorResource(
                    id = if (isEnabled) R.color.red else R.color.soft_gray
                ),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(padding)
    ) {
        GExaText(
            text = text,
            fontSize = fontSize,
            color = colorResource(id = R.color.white),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}