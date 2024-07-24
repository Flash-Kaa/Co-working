package com.na.coworking.ui.account.elements

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
internal fun ConfirmBookingDialog(
    bookingId: Int,
    onDismiss: () -> Unit,
    onEvent: (AccountEvent) -> Unit
) {
    val code = remember { mutableStateOf("") }
    val state: MutableState<LoadState> = remember { mutableStateOf(LoadState.None) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
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
            DialogDescription()
            CodeField(code, state)
            RedButton(
                text = stringResource(R.string.confirm_booking),
                onClick = onConfirmAction(bookingId, code, state, onDismiss, onEvent),
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.value != LoadState.Progress
            )
        }

        CancelButton(onDismiss)
    }
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
                contentDescription = stringResource(R.string.cancel_confirm),
                tint = colorResource(id = R.color.soft_black)
            )
        }
    }
}

@Composable
private fun Title() {
    GExaText(
        text = stringResource(id = R.string.confirm_booking),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun DialogDescription() {
    GExaText(
        text = stringResource(R.string.input_code_description),
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun onConfirmAction(
    bookingId: Int,
    code: MutableState<String>,
    state: MutableState<LoadState>,
    onDismiss: () -> Unit,
    onEvent: (AccountEvent) -> Unit
): () -> Unit = {
    val codeIntValue = code.value.toIntOrNull()
    if (codeIntValue == null) {
        state.value = LoadState.Error
    } else {
        onEvent(
            AccountEvent.OnConfirmBooking(
                bookingId = bookingId,
                code = codeIntValue,
                onSuccess = {
                    state.value = LoadState.Successful
                    onDismiss.invoke()
                },
                onError = {
                    state.value = LoadState.Error
                },
                onProgress = {
                    state.value = LoadState.Progress
                }
            )
        )
    }
}

@Composable
private fun CodeField(
    code: MutableState<String>,
    state: MutableState<LoadState>
) {
    TextField(
        value = code.value,
        onValueChange = { code.value = it },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .shadow(3.dp, RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Placeholder() },
        colors = getTextFieldColors(state),
        isError = state.value != LoadState.Error,
        enabled = state.value != LoadState.Progress
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun getAnimationContainerColor(
    state: MutableState<LoadState>
): Animatable<Color, AnimationVector4D> {
    val white = colorResource(id = R.color.white)
    val red = colorResource(id = R.color.red)
    val containerColor = remember { Animatable(white) }
        .apply {
            if (state.value == LoadState.Error) {
                LaunchedEffect(key1 = Unit) {
                    animateTo(
                        targetValue = red,
                        animationSpec = tween(durationMillis = 600)
                    )
                    animateTo(
                        targetValue = white,
                        animationSpec = tween(durationMillis = 400)
                    )

                    state.value = LoadState.None
                }
            }
        }

    return containerColor
}

@Composable
private fun getTextFieldColors(
    state: MutableState<LoadState>
): TextFieldColors {
    val containerColor = getAnimationContainerColor(state)

    return TextFieldDefaults.colors(
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedContainerColor = containerColor.value,
        unfocusedContainerColor = containerColor.value,
        disabledContainerColor = colorResource(id = R.color.soft_white),
        errorContainerColor = containerColor.value,
        errorTextColor = colorResource(id = R.color.soft_black),
        focusedTextColor = colorResource(id = R.color.soft_black),
        disabledTextColor = colorResource(id = R.color.soft_gray),
        unfocusedTextColor = colorResource(id = R.color.soft_black)
    )
}

@Composable
private fun Placeholder() {
    GExaText(
        text = stringResource(R.string.confirm_code),
        fontSize = 16.sp,
        color = colorResource(id = R.color.soft_gray)
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(Modifier.fillMaxSize()) {
        ConfirmBookingDialog(
            onDismiss = { },
            bookingId = 0,
            onEvent = { }
        )
    }
}