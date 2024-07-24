package com.na.coworking.ui.authorization

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.AuthorizationAction
import com.na.coworking.actions.AuthorizationEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
fun AuthorizationUI(
    userLogin: MutableState<UserLoginStateUI>,
    getEvent: (AuthorizationEvent) -> Unit,
    getAction: (AuthorizationAction) -> (() -> Unit)
) {
    val state = remember {
        mutableStateOf(LoadState.None)
    }

    Dialog(onDismissRequest = { }) {
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
            Fields(userLogin, state)

            RedButton(
                text = stringResource(R.string.authorize),
                onClick = {
                    getEvent(
                        AuthorizationEvent.Authorization(
                            onError = { state.value = LoadState.Error },
                            onProgress = { state.value = LoadState.Progress }
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = state.value != LoadState.Progress
            )

            Register(getAction)
        }
    }
}

@Composable
private fun Register(getAction: (AuthorizationAction) -> () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            GExaText(
                text = stringResource(R.string.have_not_account),
                fontSize = 13.sp,
                fontWeight = FontWeight(400)
            )

            TextButton(onClick = getAction(AuthorizationAction.ToRegistration)) {
                GExaText(
                    text = stringResource(R.string.register),
                    fontSize = 13.sp,
                    color = colorResource(R.color.red)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun Title() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        GExaText(
            text = stringResource(R.string.authorization_title),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }

    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
private fun Fields(
    userLogin: MutableState<UserLoginStateUI>,
    state: MutableState<LoadState>
) {
    val passwordIsVisible = remember { mutableStateOf(false) }

    InputField(
        value = userLogin.value.login,
        placeholderText = stringResource(R.string.login),
        state = state
    ) {
        userLogin.value = userLogin.value.copy(login = it)
    }

    InputField(
        value = userLogin.value.password,
        placeholderText = stringResource(R.string.password),
        state = state,
        isVisible = passwordIsVisible
    ) {
        userLogin.value = userLogin.value.copy(password = it)
    }

    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
private fun InputField(
    value: String,
    placeholderText: String,
    state: MutableState<LoadState>,
    isVisible: MutableState<Boolean>? = null,
    onValueChange: (String) -> Unit
) {
    val visualTransformation = if (isVisible?.value == false) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(5.dp),
        singleLine = true,
        modifier = Modifier
            .shadow(3.dp, RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp)
            )
            .fillMaxWidth(),
        trailingIcon = { TrailingIcon(isVisible) },
        placeholder = { Placeholder(placeholderText) },
        colors = getTextFieldColors(state),
        visualTransformation = visualTransformation,
        enabled = state.value != LoadState.Progress
    )

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun TrailingIcon(isVisible: MutableState<Boolean>?) {
    if (isVisible != null) {
        val modifier = Modifier
            .clickable(
                onClick = { isVisible.value = !isVisible.value },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .padding(5.dp)

        if (isVisible.value) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24),
                contentDescription = stringResource(R.string.off_password_visibility_cd),
                modifier = modifier
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_visibility_24),
                contentDescription = stringResource(R.string.on_password_visibility_cd),
                modifier = modifier
            )
        }
    }
}

@Composable
private fun Placeholder(text: String) {
    GExaText(
        text = text,
        fontSize = 16.sp,
        color = colorResource(id = R.color.soft_gray)
    )
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
        disabledContainerColor = colorResource(id = R.color.soft_white),
        unfocusedContainerColor = containerColor.value,
        errorContainerColor = containerColor.value,
        errorTextColor = colorResource(id = R.color.soft_black),
        focusedTextColor = colorResource(id = R.color.soft_black),
        disabledTextColor = colorResource(id = R.color.soft_black),
        unfocusedTextColor = colorResource(id = R.color.soft_black)
    )
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

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val user = remember {
        mutableStateOf(UserLoginStateUI())
    }

    AuthorizationUI(
        userLogin = user,
        getAction = { {} },
        getEvent = {}
    )
}