package com.na.coworking.ui.mainpage.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.ui.global.GExaText

@Composable
internal fun MainImage() {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        BackgroundStartImage()
        StartHelloBox()
    }
}

@Composable
private fun StartHelloBox() {
    HelloBoxBackground()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {

        HelloText()
        ToListOfCoworking()
    }
}

@Composable
private fun HelloBoxBackground() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(5.dp)
            )
            .height(100.dp)
    )
}

@Composable
private fun ToListOfCoworking() {
    GExaText(
        text = stringResource(R.string.to_wath_workspaces),
        fontSize = 13.sp,
        color = colorResource(id = R.color.white),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .offset(y = 15.dp)
            .background(
                color = colorResource(id = R.color.red),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(top = 15.dp, bottom = 15.dp, start = 35.dp, end = 35.dp)
    )
}

@Composable
private fun BackgroundStartImage() {
    Image(
        bitmap = ImageBitmap.imageResource(id = R.drawable.background_main),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
    )
}

@Composable
private fun HelloText() {
    GExaText(
        text = createHelloText(),
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 30.dp, start = 5.dp, end = 5.dp)
    )
}

@Composable
private fun createHelloText() = buildAnnotatedString {
    withStyle(SpanStyle(colorResource(id = R.color.soft_black))) {
        append("Выбирайте, сравнивайте и ")
    }

    withStyle(SpanStyle(colorResource(id = R.color.red))) {
        append("бронируйте")
    }

    withStyle(SpanStyle(colorResource(id = R.color.soft_black))) {
        append(" пространства для учёбы, встреч и мероприятий ")
    }

    withStyle(SpanStyle(colorResource(id = R.color.red))) {
        append("онлайн")
    }

    withStyle(SpanStyle(colorResource(id = R.color.soft_black))) {
        append(".")
    }
}