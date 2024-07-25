package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.na.coworking.R
import com.na.coworking.domain.entities.WorkspaceObject

@Composable
fun CoworkingScheme(
    templates: List<WorkspaceObject>,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 2.dp,
                color = colorResource(id = com.na.coworking.data.R.color.black),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = com.na.coworking.data.R.color.black),
            )
            .background(
                color = colorResource(id = R.color.white),
            )

    ) {
        TemplatesDrawer(templates, onClick)
    }
}

@Composable
private fun TemplatesDrawer(
    templates: List<WorkspaceObject>,
    onClick: (() -> Unit)?
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val scale = constraints.maxWidth.toFloat() / templates.maxOf { it.x + it.width.toFloat() }

        for (template in templates) {
            // TODO: use image
            Box(
                modifier = Modifier
                    .scaledImage(template, scale, onClick)
            )
        }
    }
}


@Composable
private fun Modifier.scaledImage(
    template: WorkspaceObject,
    scale: Float,
    onClick: (() -> Unit)? = null
): Modifier {
    val clickableOrNot =
        if (onClick == null) {
            this
        } else {
            this.clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
        }


    with(LocalDensity.current) {
        return clickableOrNot
            .padding(start = (template.x * scale).toDp(), top = (template.y * scale).toDp())
            .background(color = if (template.id == 0) Color.Red else if (template.id == 1) Color.Blue else Color.Green)
            .height((template.height * scale).toDp())
            .width((template.width * scale).toDp())
    }
}