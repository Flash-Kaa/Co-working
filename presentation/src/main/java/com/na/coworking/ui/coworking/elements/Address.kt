package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText

@Composable
fun Address(coworking: MutableState<Workspace>) {
    Column(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        GTeraText(text = stringResource(R.string.coworking_contacts), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(15.dp))

        GExaText(text = getAnnotatedString(coworking), fontSize = 16.sp)
    }
}

@Composable
private fun getAnnotatedString(coworking: MutableState<Workspace>) =
    buildAnnotatedString {
        withStyle(SpanStyle(color = colorResource(id = R.color.red), fontSize = 16.sp)) {
            append(stringResource(R.string.address))
        }

        withStyle(SpanStyle(color = colorResource(id = R.color.soft_black), fontSize = 13.sp)) {
            append(coworking.value.address)
        }
    }