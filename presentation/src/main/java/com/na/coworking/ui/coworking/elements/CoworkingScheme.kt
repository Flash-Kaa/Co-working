package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.na.coworking.R
import com.na.coworking.domain.entities.WorkspaceObject

@Composable
fun CoworkingScheme(templates: List<WorkspaceObject>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(20.dp)
            )
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(30.dp)
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
        TemplatesDrawer(templates)
    }
}

@Composable
private fun TemplatesDrawer(templates: List<WorkspaceObject>) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val scale = constraints.maxWidth.toFloat() / templates.maxOf { it.x + it.width.toFloat() }

        with(LocalDensity.current) {
            templates.forEach {
                // TODO: use image
                Box(
                    modifier = Modifier
                        .padding(start = (it.x * scale).toDp(), top = (it.y * scale).toDp())
                        .background(color = if (it.id == 0) Color.Red else if (it.id == 1) Color.Blue else Color.Green)
                        .height((it.height * scale).toDp())
                        .width((it.width * scale).toDp())
                )
            }
        }
    }
}