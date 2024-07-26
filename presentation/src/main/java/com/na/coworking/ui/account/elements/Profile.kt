package com.na.coworking.ui.account.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.ui.account.UserStateUI
import com.na.coworking.ui.global.GExaText

@Composable
fun Profile(user: MutableState<UserStateUI>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .background(colorResource(id = R.color.soft_white))
            .padding(30.dp)
    ) {
        TextWithTitle(
            text = "${user.value.firstName} ${user.value.secondName}",
            title = stringResource(id = R.string.user)
        )

        Spacer(modifier = Modifier.height(35.dp))

        TextWithTitle(text = user.value.email, title = stringResource(id = R.string.email))
    }
}

@Composable
private fun TextWithTitle(text: String, title: String) {
    GExaText(
        text = title,
        fontSize = 13.sp,
        color = colorResource(id = R.color.soft_gray),
        fontWeight = FontWeight(400)
    )

    GExaText(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight(400)
    )
}