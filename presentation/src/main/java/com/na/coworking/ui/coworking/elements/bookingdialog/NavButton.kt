package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.ui.global.RedButton

@Composable
fun NavButton(onClick: () -> Unit, isEnabled: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        RedButton(
            text = stringResource(R.string.continue_str),
            onClick = onClick,
            fontSize = 13.sp,
            padding = 12.dp,
            isEnabled = isEnabled
        )
    }
}