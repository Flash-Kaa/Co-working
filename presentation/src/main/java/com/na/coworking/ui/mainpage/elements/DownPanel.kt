package com.na.coworking.ui.mainpage.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.ui.global.GExaText

@Composable
internal fun DownPanel() {
    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.soft_black))
            .fillMaxWidth()
            .height(150.dp)
    ) {
        GExaText(
            text = stringResource(id = R.string.contacts),
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(top = 30.dp, start = 30.dp)
        )
    }
}