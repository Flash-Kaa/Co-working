package com.na.coworking.ui.authorization

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
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
fun AuthorizationUI(
    userLogin: MutableState<UserLoginStateUI>,
    onDismiss: () -> Unit,
    getEvent: (AuthorizationEvent) -> Unit,
    getAction: (AuthorizationAction) -> (() -> Unit)
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
            Fields(userLogin)

            RedButton(
                text = stringResource(R.string.authorize),
                onClick = {
                    // TODO: onSuccess {}, inProgress {}, onError {} (incorrect data)
                    getEvent(AuthorizationEvent.Authorization(userLogin.value))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Register(getAction)
        }

        CancelButton(onDismiss)
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
private fun Fields(userLogin: MutableState<UserLoginStateUI>) {
    val passwordIsVisible = remember {
        mutableStateOf(false)
    }

    InputField(userLogin.value.login, stringResource(R.string.login)) {
        userLogin.value = userLogin.value.copy(login = it)
    }

    InputField(
        value = userLogin.value.password,
        placeholderText = stringResource(R.string.password),
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
        colors = getTextFieldColors(),
        visualTransformation = visualTransformation
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
private fun getTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedContainerColor = colorResource(id = R.color.white),
        unfocusedContainerColor = colorResource(id = R.color.white),
        errorContainerColor = colorResource(id = R.color.white),
        errorTextColor = colorResource(id = R.color.soft_black),
        focusedTextColor = colorResource(id = R.color.soft_black),
        unfocusedTextColor = colorResource(id = R.color.soft_black)
    )
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

    Spacer(modifier = Modifier.height(20.dp))
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val user = remember {
        mutableStateOf(UserLoginStateUI())
    }

    AuthorizationUI(
        userLogin = user,
        onDismiss = { },
        getAction = { {} },
        getEvent = {}
    )
}