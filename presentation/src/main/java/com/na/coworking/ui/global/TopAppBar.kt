package com.na.coworking.ui.global

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.actions.GlobalAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    getAction: (GlobalAction) -> (() -> Unit)
) {
    val isOpen = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = { Title(onClick = getAction(GlobalAction.ToMainPage)) },
        modifier = Modifier.shadow(3.dp),
        actions = {
            ActionList(getAction, isOpen)
        },
    )

    SideBar(isOpen, getAction)
}

@Composable
private fun ActionList(
    getAction: (GlobalAction) -> () -> Unit,
    isOpen: MutableState<Boolean>
) {
    ActionButton(
        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_phone_enabled_24),
        contentDescription = "contact",
        onClick = getAction(GlobalAction.ToContacts)
    )

    ActionButton(
        imageVector = ImageVector.vectorResource(id = R.drawable.outline_person_24),
        contentDescription = "personal account",
        onClick = getAction(GlobalAction.ToPersonAccount)
    )

    NavigationIcon {
        isOpen.value = true
    }
}

@Composable
private fun SideBar(
    isOpen: MutableState<Boolean>,
    getAction: (GlobalAction) -> () -> Unit
) {
    LeftSideBar(isOpen = isOpen) {
        Column {
            LeftSizeTextButton(
                text = stringResource(R.string.personal_account),
                onClick = {
                    getAction(GlobalAction.ToPersonAccount).invoke()
                    isOpen.value = false
                }
            )
            LeftSizeTextButton(
                text = stringResource(R.string.coworkings),
                onClick = {
                    getAction(GlobalAction.ToListOfCoworking).invoke()
                    isOpen.value = false
                }
            )
            LeftSizeTextButton(
                text = stringResource(R.string.contacts),
                onClick = {
                    getAction(GlobalAction.ToContacts).invoke()
                    isOpen.value = false
                }
            )
        }
    }
}

@Composable
private fun Title(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.logo),
            contentDescription = "site logo",
            modifier = Modifier
                .padding(top = 5.dp)
                .heightIn(max = 60.dp)
        )

        Column {
            SiteName(Modifier)
            Description(Modifier.width(120.dp))
        }
    }
}

@Composable
private fun ActionButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = colorResource(id = R.color.soft_gray),
            modifier = Modifier
                .border(BorderStroke(2.dp, colorResource(id = R.color.soft_gray)), CircleShape)
                .padding(5.dp)
        )
    }
}

@Composable
private fun NavigationIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_24),
            contentDescription = "left navigation bar",
            tint = colorResource(id = R.color.soft_gray),
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
private fun SiteName(modifier: Modifier) {
    GExaText(
        text = getAnnotatedSiteName(
            primaryColor = colorResource(id = R.color.soft_black),
            secondaryColor = colorResource(id = R.color.red)
        ),
        fontSize = 13.sp,
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
private fun Description(modifier: Modifier) {
    GExaText(
        text = stringResource(R.string.site_description),
        color = colorResource(id = R.color.gray),
        fontSize = 10.sp,
        minLines = 2,
        maxLines = 2,
        modifier = modifier,
        lineHeight = 12.sp
    )
}

@Composable
private fun LeftSizeTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        GExaText(
            text = text,
            fontSize = 18.sp
        )
    }
}

private fun getAnnotatedSiteName(
    primaryColor: Color,
    secondaryColor: Color
): AnnotatedString =
    buildAnnotatedString {
        // Is site name
        withStyle(SpanStyle(color = primaryColor)) {
            append("co-")
        }

        withStyle(SpanStyle(color = secondaryColor)) {
            append("work")
        }

        withStyle(SpanStyle(color = primaryColor)) {
            append("ing")
        }
    }

@Preview(showBackground = false)
@Composable
private fun Preview() {
    Scaffold(
        topBar = { com.na.coworking.ui.global.TopAppBar({ {} }) }
    ) {
        GExaText(text = "Hello!", fontSize = 24.sp, modifier = Modifier.padding(it))
    }
}