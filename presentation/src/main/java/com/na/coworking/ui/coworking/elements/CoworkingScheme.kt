package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GTeraText

@Composable
fun CoworkingScheme(coworking: MutableState<Workspace>) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        GTeraText(
            text = stringResource(R.string.coworking_scheme),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))


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
            TemplatesDrawer(coworking)
        }
    }
}

@Composable
private fun TemplatesDrawer(coworking: MutableState<Workspace>) {
    // TODO: add scheme
    Box(
        modifier = Modifier
        .fillMaxSize()
        .height(100.dp)
    )
}