package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText

@Composable
fun Description(coworking: MutableState<Workspace>) {
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Title()
        Spacer(modifier = Modifier.height(10.dp))
        ContentBox(coworking)
    }
}

@Composable
private fun ContentBox(coworking: MutableState<Workspace>) {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .shadow((1.5).dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        GExaText(
            text = coworking.value.description,
            fontSize = 13.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun Title() {
    GTeraText(text = stringResource(R.string.about_coworking), fontSize = 16.sp)
}