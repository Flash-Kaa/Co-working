package com.na.coworking.ui.account.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.actions.AccountAction
import com.na.coworking.domain.entities.User
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText

@Composable
internal fun UserTitleBg(
    user: User,
    getAction: (AccountAction) -> (() -> Unit)
) {
    Box(
        modifier = Modifier.height(230.dp)
    ) {
        ImageBg()
        Title()

        Row(
            modifier = Modifier.padding(30.dp)
        ) {
            CircleIcon(user)
            Spacer(modifier = Modifier.width(10.dp))
            UserName(user)

            ExitButton(onClick = getAction(AccountAction.OnExit))
        }
    }
}

@Composable
private fun ExitButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp)
    ) {
        TextButton(onClick = onClick) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.soft_black))
                    .height(20.dp)
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            GExaText(text = stringResource(R.string.exit), fontSize = 13.sp)
        }
    }
}

@Composable
private fun UserName(user: User) {
    Box(
        modifier = Modifier.heightIn(min = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        GExaText(
            text = ("${user.firstName} ${user.secondName}").ifBlank { user.email },
            fontSize = 13.sp
        )
    }
}

@Composable
private fun CircleIcon(user: User) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.soft_black),
                shape = CircleShape
            )
            .sizeIn(minWidth = 30.dp, minHeight = 30.dp)
    ) {
        GExaText(
            text = user.email.getOrElse(0) { 'П' }.uppercase(),
            color = colorResource(id = R.color.white),
            fontSize = 13.sp,
        )
    }
}

@Composable
private fun ImageBg() {
    Image(
        bitmap = ImageBitmap.imageResource(id = R.drawable.personal_account_bg),
        alignment = Alignment { size, _: IntSize, _ ->
            IntOffset(-size.width / 2, 0)
        },
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun Title() {
    GTeraText(
        text = stringResource(id = R.string.personal_account),
        fontSize = 20.sp,
        lineHeight = 18.sp,
        minLines = 2,
        maxLines = 2,
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight()
            .padding(start = 30.dp)
    )
}