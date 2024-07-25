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
    onClick: ((WorkspaceObject) -> Unit)? = null
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
    onClick: ((WorkspaceObject) -> Unit)?
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val width = templates.maxOf { it.x + it.width.toFloat() }
        val scale = constraints.maxWidth.toFloat() / width

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
    onClick: ((WorkspaceObject) -> Unit)? = null
): Modifier {
    val modifier = this
    with(LocalDensity.current) {
        val offset =
            modifier.padding(start = (template.x * scale).toDp(), top = (template.y * scale).toDp())

        val clickableOrNot =
            if (onClick == null) {
                offset
            } else {
                offset.clickable(
                    onClick = { onClick(template) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.Gray)
                )
            }

        // TODO: delete
        val color = when (template.id) {
            0 -> {
                Color.Red
            }

            1 -> {
                Color.Blue
            }

            else -> {
                Color.Green
            }
        }

        val mod = clickableOrNot
            .background(color = color)
            .height((template.height * scale).toDp())
            .width((template.width * scale).toDp())

        if (template.isEnableToChosen) {
            return mod
        } else {
            return mod.background(colorResource(id = R.color.light_gray).copy(alpha = 0.9f))
        }
    }
}