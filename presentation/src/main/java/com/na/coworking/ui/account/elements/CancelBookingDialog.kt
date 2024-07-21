package com.na.coworking.ui.account.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.AccountAction
import com.na.coworking.ui.global.GExaText

@Composable
fun CancelBookingDialog(
    bookingId: Int,
    onDismiss: () -> Unit,
    getAction: (AccountAction) -> (() -> Unit)
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Title()
            TextContent()

            AnswerButtons(getAction, bookingId, onDismiss)
        }

        CancelButton(onDismiss)
    }
}

@Composable
private fun AnswerButtons(
    getAction: (AccountAction) -> () -> Unit,
    bookingId: Int,
    onDismiss: () -> Unit
) {
    val spacerWeight = 1f
    val buttonWeight = 4f

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.weight(spacerWeight))
        Button(
            text = stringResource(id = R.string.yes),
            onClick = {
                getAction(AccountAction.OnCancelBooking(bookingId)).invoke()
                onDismiss.invoke()
            },
            modifier = Modifier.weight(buttonWeight)
        )
        Spacer(modifier = Modifier.weight(spacerWeight))

        Button(
            text = stringResource(id = R.string.no),
            onClick = onDismiss,
            modifier = Modifier.weight(buttonWeight)
        )
        Spacer(modifier = Modifier.weight(spacerWeight))
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun Button(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .shadow(4.dp, RoundedCornerShape(5.dp))
            .background(
                color = colorResource(id = R.color.red),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
    ) {
        GExaText(
            text = text,
            fontSize = 16.sp,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TextContent() {
    GExaText(
        text = stringResource(R.string.are_your_want_to_cancal_booking),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun Title() {
    GExaText(
        text = stringResource(R.string.are_your_shoure),
        fontSize = 16.sp,
        color = colorResource(R.color.red),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun CancelButton(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(onClick = onDismiss) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                contentDescription = stringResource(R.string.not_cancel),
                tint = colorResource(id = R.color.soft_black)
            )
        }
    }
}